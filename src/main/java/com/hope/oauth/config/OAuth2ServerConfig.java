package com.hope.oauth.config;

import com.hope.oauth.config.handler.BataApprovalHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.header.HeaderWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

/**
 * OAuth2ServerConfig
 * 授权服务配置类
 *
 * @author 低调小熊猫(aodeng)
 * @date 2020 -04-15
 */
@Configuration
public class OAuth2ServerConfig {

    /**
     * The type Resource server configuration.
     *
     * @author aodeng
     */
    @Configuration
    protected static class ResourceServerConfig {

        @Autowired
        private DataSource dataSource;

        @Bean
        protected org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration ApiResources() {

            org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration resource = new org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration() {
                public void setConfigurers(List<ResourceServerConfigurer> configurers) {
                    super.setConfigurers(configurers);
                }
            };

            resource.setConfigurers(Arrays.<ResourceServerConfigurer>asList(new ResourceServerConfigurerAdapter() {

                @Override
                public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
                    TokenStore tokenStore = new JdbcTokenStore(dataSource);
                    resources.resourceId("api-services").tokenStore(tokenStore).stateless(true);
                }

                @Override
                public void configure(HttpSecurity http) throws Exception {
                    http
                            .sessionManagement()
                            .sessionCreationPolicy(SessionCreationPolicy.NEVER)
                            .and()
                            .requestMatchers().antMatchers("/api/**")
                            .and()
                            .authorizeRequests()
                            .antMatchers(HttpMethod.GET, "/api/**").access("#oauth2.hasScope('read')")
                            .antMatchers(HttpMethod.POST, "/api/**").access("#oauth2.hasScope('write')")
                            .antMatchers(HttpMethod.PATCH, "/api/**").access("#oauth2.hasScope('write')")
                            .antMatchers(HttpMethod.PUT, "/api/**").access("#oauth2.hasScope('write')")
                            .antMatchers(HttpMethod.DELETE, "/api/**").access("#oauth2.hasScope('write')")
                            .and()
                            .headers().addHeaderWriter(new HeaderWriter() {
                        @Override
                        public void writeHeaders(HttpServletRequest request, HttpServletResponse response) {
                            response.addHeader("Access-Control-Allow-Origin", "*");
                            if (request.getMethod().equals("OPTIONS")) {
                                response.setHeader("Access-Control-Allow-Methods", request.getHeader("Access-Control-Request-Method"));
                                response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
                            }
                        }
                    });
                }

            }));
            resource.setOrder(4);

            return resource;

        }

        @Bean
        protected org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration UserResources() {

            org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration resource = new org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration() {
                public void setConfigurers(List<ResourceServerConfigurer> configurers) {
                    super.setConfigurers(configurers);
                }
            };

            resource.setConfigurers(Arrays.<ResourceServerConfigurer>asList(new ResourceServerConfigurerAdapter() {

                @Override
                public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
                    TokenStore tokenStore = new JdbcTokenStore(dataSource);
                    resources.resourceId("user-services").tokenStore(tokenStore).stateless(true);
                }

                @Override
                public void configure(HttpSecurity http) throws Exception {
                    http
                            .sessionManagement()
                            .sessionCreationPolicy(SessionCreationPolicy.NEVER)
                            .and()
                            .requestMatchers().antMatchers("/user/**")
                            .and()
                            .authorizeRequests()
                            .antMatchers(HttpMethod.GET, "/user/**").access("#oauth2.hasScope('read')")
                            .antMatchers(HttpMethod.POST, "/user/**").access("#oauth2.hasScope('write')")
                            .antMatchers(HttpMethod.PATCH, "/user/**").access("#oauth2.hasScope('write')")
                            .antMatchers(HttpMethod.PUT, "/user/**").access("#oauth2.hasScope('write')")
                            .antMatchers(HttpMethod.DELETE, "/user/**").access("#oauth2.hasScope('write')")
                            .and()
                            .headers().addHeaderWriter(new HeaderWriter() {
                        @Override
                        public void writeHeaders(HttpServletRequest request, HttpServletResponse response) {
                            response.addHeader("Access-Control-Allow-Origin", "*");
                            if (request.getMethod().equals("OPTIONS")) {
                                response.setHeader("Access-Control-Allow-Methods", request.getHeader("Access-Control-Request-Method"));
                                response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
                            }
                        }
                    });
                }

            }));
            resource.setOrder(3);

            return resource;

        }
    }

    /**
     * The type Authorization server configuration.
     * 参考：https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql
     * 把客户信息持久化到数据库
     *
     * @author aodeng
     */
    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private DataSource dataSource;
        @Autowired
        private AuthenticationManager authenticationManager;
        @Autowired
        private UserDetailsService userDetailsService;

        @Bean
        public JdbcClientDetailsService clientDetailsService() {
            return new JdbcClientDetailsService(dataSource);
        }

        @Bean
        public TokenStore tokenStore() {
            return new JdbcTokenStore(dataSource);
        }

        /**
         * Authorization code services authorization code services.
         * 加入对授权码模式的支持
         *
         * @return the authorization code services
         * @author aodeng
         */
        @Bean
        public AuthorizationCodeServices authorizationCodeServices() {
            return new JdbcAuthorizationCodeServices(dataSource);
        }

        @Bean
        public ApprovalStore approvalStore() {
            return new JdbcApprovalStore(dataSource);
        }

        @Bean
        public OAuth2AccessDeniedHandler oauth2AccessDeniedHandler() {
            return new OAuth2AccessDeniedHandler();
        }

        @Bean
        public OAuth2AuthenticationEntryPoint oauth2AuthenticationEntryPoint() {
            return new OAuth2AuthenticationEntryPoint();
        }

        @Bean
        public OAuth2RequestFactory oauth2RequestFactory() {
            return new DefaultOAuth2RequestFactory(clientDetailsService());
        }

        @Bean
        public UserApprovalHandler userApprovalHandler() {
            return new BataApprovalHandler(clientDetailsService(), approvalStore(), oauth2RequestFactory());
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.withClientDetails(clientDetailsService());
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
            oauthServer
                    .tokenKeyAccess("permitAll()")
                    .checkTokenAccess("permitAll()")
                    .accessDeniedHandler(oauth2AccessDeniedHandler())
                    .authenticationEntryPoint(oauth2AuthenticationEntryPoint())
                    .allowFormAuthenticationForClients();//允许表单认证

        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
            /*声明授权，token端点，token服务配置信息：存储方式，有效期...*/
            endpoints
                    .userDetailsService(userDetailsService)
                    .userApprovalHandler(userApprovalHandler())
                    .approvalStore(approvalStore())
                    .authorizationCodeServices(authorizationCodeServices())
                    .authenticationManager(authenticationManager)
                    .tokenStore(tokenStore());
        }
    }
}
