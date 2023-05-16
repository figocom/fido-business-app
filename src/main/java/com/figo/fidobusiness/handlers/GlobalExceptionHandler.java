package com.figo.fidobusiness.handlers;


import com.figo.fidobusiness.exception.PermissionDeniedException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("com.figo.fidobusiness")
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView exception(Model model, Exception e) {
        e.printStackTrace();
        System.out.println(e.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("errors/500");
        return mav;
    }



    @ExceptionHandler(PermissionDeniedException.class)
    public ModelAndView exception1() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("errors/403");
        return mav;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView exception6() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("errors/403");
        return mav;
    }

    @ExceptionHandler(AuthenticationException.class)
    public ModelAndView exception2() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("errors/401");
        return mav;
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ModelAndView exception3() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("errors/403");
        return mav;
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ModelAndView exception4() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("errors/404");
        return mav;
    }
}
