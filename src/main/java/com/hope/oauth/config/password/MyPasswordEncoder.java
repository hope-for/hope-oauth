package com.hope.oauth.config.password;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * MyPasswordEncoder
 *
 * @author 低调小熊猫(aodeng)
 * @date 2020-04-23
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence);
    }
}
