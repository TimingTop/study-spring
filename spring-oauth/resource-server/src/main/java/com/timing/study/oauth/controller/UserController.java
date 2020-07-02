package com.timing.study.oauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {


    @RequestMapping("/hello")
    public String hello() {
        return "world";
    }
}
