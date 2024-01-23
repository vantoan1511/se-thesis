package com.newswebsite.main.controller.web;

import com.newswebsite.main.service.articleservice.IArticleReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "WebHomeController")
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private IArticleReader articleRetrievalService;

    @GetMapping
    public ModelAndView getHomePage() {
        String viewName = "web/home";
        ModelAndView view = new ModelAndView(viewName);
        return view;
    }
}
