package com.timing.study.authentication.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping("/hello")
    @ResponseBody
    public String home() {
        return " hello.";
    }

    @RequestMapping("/info")
    @ResponseBody
    public String info() {
        String currentUser = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            currentUser = ((UserDetails)principal).getUsername();
        } else {
            currentUser = principal.toString();
        }
        return "current user is " + currentUser;
    }
}
