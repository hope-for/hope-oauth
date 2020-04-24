package com.hope.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

/**
 * CorsConfig
 *
 * @author 低调小熊猫(aodeng)
 * @date 2020-04-22
 */
@Configuration
public class CorsConfig {

    private CorsConfiguration configuration() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        return configuration;
    }

/*    @Bean
    public FilterRegistration corsFilter(){
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration());
    }*/
}
