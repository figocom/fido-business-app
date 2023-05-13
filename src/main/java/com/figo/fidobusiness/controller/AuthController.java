package com.figo.fidobusiness.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/auth")
public interface AuthController {
    @PostMapping("/login")
    ModelAndView login();
}
