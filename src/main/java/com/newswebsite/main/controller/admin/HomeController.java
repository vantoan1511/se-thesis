package com.newswebsite.main.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminHomeController")
@RequestMapping("/admin")
public class HomeController {

    @GetMapping({"/home", "/"})
    public String getHomePage() {
        return "admin/home";
    }
}
