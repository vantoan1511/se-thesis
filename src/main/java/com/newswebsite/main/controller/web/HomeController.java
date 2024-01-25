package com.newswebsite.main.controller.web;

import com.newswebsite.main.service.articleservice.IArticleReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "WebHomeController")
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private IArticleReader articleReader;

    @GetMapping
    public ModelAndView getHomePage() {
        String viewName = "web/home";
        ModelAndView view = new ModelAndView(viewName);
        view.addObject("featured", articleReader.getFeaturedArticles(new PageRequest(0, 3)));
        view.addObject("latest", articleReader.getLatestArticles(new PageRequest(0, 10)));
        return view;
    }
}
