package com.newswebsite.main.controller.advice;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final String DEFAULT_ADMIN_ERROR_VIEW = "admin/404";
    private final String DEFAULT_WEB_ERROR_VIEW = "web/404";

    //@ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(Exception ex, HttpServletRequest req) throws Exception {
        if (AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null) {
            throw ex;
        }
        String viewName = isAdminRequest(req) ? DEFAULT_ADMIN_ERROR_VIEW : DEFAULT_WEB_ERROR_VIEW;
        return new ModelAndView(viewName);
    }

    private boolean isAdminRequest(HttpServletRequest req) {
        return req.getRequestURI().contains("/admin/");
    }
}
