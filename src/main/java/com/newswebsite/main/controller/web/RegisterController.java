package com.newswebsite.main.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @GetMapping
    public ModelAndView getLoginPage() {
        String viewName = "web/register";
        ModelAndView view = new ModelAndView(viewName);
        return view;
    }
}
