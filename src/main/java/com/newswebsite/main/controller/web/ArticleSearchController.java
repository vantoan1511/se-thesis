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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/search")
public class ArticleSearchController {

    private final ICategoryReader categoryReader;

    private final IArticleReader articleReader;

    @Autowired
    public ArticleSearchController(ICategoryReader categoryReader, IArticleReader articleReader) {
        this.categoryReader = categoryReader;
        this.articleReader = articleReader;
    }

    @GetMapping
    public String search(
            @RequestParam(value = "q", required = false, defaultValue = "") String q,
            @RequestParam(value = "date_format", required = false, defaultValue = "anytime") String dateFormat,
            @RequestParam(value = "categoryId", required = false) List<Long> categoryIds,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "2") int size,
            Model model
    ) {
        Date startDate;
        switch (dateFormat) {
            case "day" ->
                    startDate = Date.from(LocalDate.now().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
            case "week" ->
                    startDate = Date.from(LocalDate.now().minusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant());
            case "month" ->
                    startDate = Date.from(LocalDate.now().minusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant());
            default -> startDate = null;
        }
        Pageable pageable = new PageRequest(page - 1, size, Sort.Direction.DESC, "publishedAt");
        model.addAttribute("articles", articleReader.search(q, categoryIds, startDate, pageable));
        model.addAttribute("categories", categoryReader.getAll());
        model.addAttribute("categoryIds", categoryIds);
        model.addAttribute("date_format", dateFormat);
        return "web/search";
    }
}
