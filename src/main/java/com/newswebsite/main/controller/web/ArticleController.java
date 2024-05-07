package com.newswebsite.main.controller.web;

import com.newswebsite.main.dto.response.ArticleResponse;
import com.newswebsite.main.enums.Role;
import com.newswebsite.main.security.SecurityUtil;
import com.newswebsite.main.service.articleservice.IArticleReader;
import com.newswebsite.main.service.articleservice.IArticleWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller(value = "WebArticleController")
@RequestMapping("/{alias}")
public class ArticleController {

    private final IArticleReader articleReader;

    private final IArticleWriter articleWriter;

    @Autowired
    public ArticleController(IArticleReader articleReader, IArticleWriter articleWriter) {
        this.articleReader = articleReader;
        this.articleWriter = articleWriter;
    }

    @GetMapping
    public ModelAndView getDetails(
            @PathVariable("alias") String alias,
            @RequestParam(value = "previewMode", required = false) boolean previewMode
    ) {
        String viewName = "web/articleDetails";
        List<String> roles = SecurityUtil.getAuthorities();

        ArticleResponse articleResponse = (previewMode && (roles.contains(Role.WRITER.name()) || roles.contains(Role.ADMIN.name()))) ?
                articleReader.getByAlias(alias) :
                articleReader.getPublishedArticle(alias);

        articleWriter.increaseTraffic(alias);

        ModelAndView view = new ModelAndView(viewName);
        view.addObject("article", articleResponse);
        return view;
    }
}
