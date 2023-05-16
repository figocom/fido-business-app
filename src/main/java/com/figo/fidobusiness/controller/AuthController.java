package com.figo.fidobusiness.controller;

import com.figo.fidobusiness.dto.LoginDTO;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/auth")
public interface AuthController {
    @GetMapping ("/login")
    ModelAndView login(@RequestParam(required = false) String error);
}
