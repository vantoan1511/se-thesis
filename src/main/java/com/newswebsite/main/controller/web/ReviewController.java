package com.newswebsite.main.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @PostMapping
    public ModelAndView create() {

        String viewName = "";
        ModelAndView view = new ModelAndView(viewName);
        return view;
    }
}
