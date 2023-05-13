package com.figo.fidobusiness.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class AuthControllerImpl implements AuthController{

    @Override
    public ModelAndView login() {
        return new ModelAndView("auth/login");
    }
}
