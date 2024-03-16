package com.newswebsite.main.controller.web;

import com.newswebsite.main.service.articleservice.IArticleReader;
import com.newswebsite.main.service.categoryservice.ICategoryReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller(value = "WebCategoryController")
@RequestMapping("/categories")
public class CategoryController {

    private final ICategoryReader categoryReader;

    private final IArticleReader articleReader;

    @Autowired
    public CategoryController(ICategoryReader categoryReader, IArticleReader articleReader) {
        this.categoryReader = categoryReader;
        this.articleReader = articleReader;
    }

    @GetMapping("/{categoryAlias}")
    public String getCategorizedArticles(@PathVariable("categoryAlias") String categoryAlias,
                                         @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                         @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                                         Model model) {
        Pageable pageable = new PageRequest(page - 1, size, Sort.Direction.DESC, "publishedAt");
        model.addAttribute("articles", articleReader.getAllPublishedByCategory(categoryAlias, pageable));
        model.addAttribute("category", categoryReader.getCategory(categoryAlias));
        return "web/category";
    }
}
