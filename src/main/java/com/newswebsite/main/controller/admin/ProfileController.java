package com.newswebsite.main.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/my-profile")
public class ProfileController {

    @GetMapping
    public ModelAndView getPage() {
        String viewName = "admin/profile/profile";
        ModelAndView view = new ModelAndView(viewName);
        return view;
    }
}
