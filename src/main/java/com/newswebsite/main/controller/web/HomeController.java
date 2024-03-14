package com.newswebsite.main.controller.web;

import com.newswebsite.main.dto.response.FeaturedArticleResponse;
import com.newswebsite.main.dto.response.LatestArticleResponse;
import com.newswebsite.main.dto.response.PopularArticleResponse;
import com.newswebsite.main.service.articleservice.IArticleReader;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller(value = "WebHomeController")
@RequestMapping({"/home", "/"})
public class HomeController {

    private final IArticleReader articleReader;

    public HomeController(IArticleReader articleReader) {
        this.articleReader = articleReader;
    }

    @GetMapping
    public ModelAndView getHomePage() {
        String viewName = "web/home";
        ModelAndView view = new ModelAndView(viewName);
        Pageable pageable = new PageRequest(0, 10, Sort.Direction.DESC, "traffic");
        List<PopularArticleResponse> popularArticles = articleReader.getAllPublished(pageable).getContent();
        List<FeaturedArticleResponse> featuredArticles = articleReader.getFeaturedArticles(new PageRequest(0, 99)).getContent();
        List<LatestArticleResponse> latestArticles = articleReader.getLatestArticles(new PageRequest(0, 10)).getContent();
        view.addObject("latest", latestArticles);
        view.addObject("featured", featuredArticles);
        view.addObject("popularArticles", popularArticles);
        return view;
    }
}
