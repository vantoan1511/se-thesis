package com.newswebsite.main.controller.admin;

import com.newswebsite.main.service.articleservice.IArticleReader;
import com.newswebsite.main.service.categoryservice.ICategoryReader;
import com.newswebsite.main.service.imageservice.IImageReader;
import com.newswebsite.main.service.userservice.IUserReader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminHomeController")
@RequestMapping("/admin")
public class HomeController {

    private final IArticleReader articleReader;
    private final IUserReader userReader;
    private final ICategoryReader categoryReader;
    private final IImageReader imageReader;

    public HomeController(
            IArticleReader articleReader,
            IUserReader userReader,
            ICategoryReader categoryReader,
            IImageReader imageReader
    ) {
        this.articleReader = articleReader;
        this.userReader = userReader;
        this.categoryReader = categoryReader;
        this.imageReader = imageReader;
    }

    @GetMapping({"/home", "/"})
    public String getHomePage(Model model) {
        model.addAttribute("totalArticles", articleReader.countTotalArticles());
        model.addAttribute("totalUsers", userReader.countTotalUsers());
        model.addAttribute("totalCategories", categoryReader.countTotalCategories());
        model.addAttribute("totalImages", imageReader.countTotalImages());
        return "admin/home";
    }
}
