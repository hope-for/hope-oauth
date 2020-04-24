package com.hope.oauth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 *
 * @author 低调小熊猫(aodeng)
 * @date 2020-04-23
 */
@RestController
@RequestMapping("user")
public class UserController {

    @RequestMapping("hello")
    public String hello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "Hello World";
    }
}
