package com.newswebsite.main.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "WebHomeController")
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String getHomePage() {
        return "web/home";
    }
}
