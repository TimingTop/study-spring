package com.timing.study.springmvc.controller;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController implements Controller {

    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        ModelAndView mv = new ModelAndView();
        mv.addObject("user", "aaaa");
        mv.setViewName("/jsp/user.jsp");
        return mv;
    }
}
