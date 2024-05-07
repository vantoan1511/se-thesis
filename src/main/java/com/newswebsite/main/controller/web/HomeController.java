package com.newswebsite.main.controller.web;

import com.newswebsite.main.dto.response.ArticleResponse;
import com.newswebsite.main.service.articleservice.IArticleReader;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller(value = "WebHomeController")
@RequestMapping({"/home", "/"})
public class HomeController {

    private final IArticleReader articleReader;

    public HomeController(IArticleReader articleReader) {
        this.articleReader = articleReader;
    }

    @GetMapping
    public String getHomePage(Model model) {
        String viewName = "web/home";
        Pageable pageable = new PageRequest(0, 10, Sort.Direction.DESC, "traffic");
        List<ArticleResponse> popularArticles = articleReader.getAllPublishedArticles(pageable).getContent();
        List<ArticleResponse> featuredArticles = articleReader.getFeaturedArticles(new PageRequest(0, 99)).getContent();
        List<ArticleResponse> latestArticles = articleReader.getLatestArticles(new PageRequest(0, 10)).getContent();
        model.addAttribute("latest", latestArticles);
        model.addAttribute("featured", featuredArticles);
        model.addAttribute("popularArticles", popularArticles);
        return viewName;
    }
}
