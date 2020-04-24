package com.hope.oauth.config.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * SecurityAuthenticationProvider
 *
 * @author 低调小熊猫(aodeng)
 * @date 2020-04-23
 */
@Component("securityAuthenticationProvider")
@Slf4j
public class SecurityAuthenticationProvider implements AuthenticationProvider {


	@Autowired
    UserDetailsService securityUserService;

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication ) throws AuthenticationException {

        // [1] 获取 username 和 password
		String userName = (String) authentication.getPrincipal();
        String inputPassword = (String) authentication.getCredentials();

        // [2] 使用用户名从数据库读取用户信息
        UserDetails userDetails = securityUserService.loadUserByUsername(userName);

     	// [3] 检查用户信息
        if(userDetails == null) {
            throw new UsernameNotFoundException(userName + " 用户不存在");
        }

        // [4] 数据库用户的密码，一般都是加密过的
        String encryptedPassword = userDetails.getPassword();

        // 根据加密算法加密用户输入的密码，然后和数据库中保存的密码进行比较
        if(!passwordEncoder.matches(inputPassword, encryptedPassword)) {
            throw new BadCredentialsException(userName + " 输入账号或密码不正确");
        }

        // [5] 成功登陆，把用户信息提交给 Spring Security
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
	}

	@Override
	public boolean supports( Class<?> authentication ) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
