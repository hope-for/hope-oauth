package com.hope.oauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ApiController
 *
 * @author 低调小熊猫(aodeng)
 * @date 2020-04-23
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String index() {
        return "login";
    }

}