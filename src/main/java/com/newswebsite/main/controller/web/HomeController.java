package com.newswebsite.main.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "WebHomeController")
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public ModelAndView getHomePage() {
        String viewName = "web/home";
        ModelAndView view = new ModelAndView(viewName);
        return view;
    }
}