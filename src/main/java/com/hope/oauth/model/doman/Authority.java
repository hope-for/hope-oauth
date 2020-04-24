package com.hope.oauth.model.doman;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * Authority
 *
 * @author 低调小熊猫(aodeng)
 * @date 2020-04-23
 */
@Data
@AllArgsConstructor
public class Authority implements GrantedAuthority {

    private Long id;
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
