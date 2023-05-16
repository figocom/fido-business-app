package com.figo.fidobusiness.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@RequestMapping("/home")
@Controller
@PreAuthorize("hasAnyRole('DIRECTOR','CORDINATOR','ADMIN')")
public class HomeControllerImpl  {
    @GetMapping
    public ModelAndView home() {
        return new ModelAndView("user/home");
    }


}
