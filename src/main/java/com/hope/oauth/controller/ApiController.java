package com.hope.oauth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ApiController
 *
 * @author 低调小熊猫(aodeng)
 * @date 2020-04-23
 */
@RestController
@RequestMapping("api")
public class ApiController {

    @RequestMapping("hello")
    public String hello() {
        return "Hello World";
    }
}
