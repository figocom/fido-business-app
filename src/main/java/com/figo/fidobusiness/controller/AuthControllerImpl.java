package com.figo.fidobusiness.controller;

import com.figo.fidobusiness.dao.AuthUserDAO;
import com.figo.fidobusiness.domain.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController{
     private final PasswordEncoder passwordEncoder;
     private final AuthUserDAO authUserDAO;



    @Override
    public ModelAndView login( String error) {
        var modelAndView = new ModelAndView("auth/login");
        modelAndView.addObject("error", error);
        return modelAndView;
    }



}
