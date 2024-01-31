package com.newswebsite.main.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(Exception ex, HttpServletRequest req) {
        String viewName = isAdminRequest(req) ? "admin/404" : "web/404";
        return new ModelAndView(viewName);
    }

    private boolean isAdminRequest(HttpServletRequest req) {
        return req.getRequestURI().contains("/admin/");
    }
}
