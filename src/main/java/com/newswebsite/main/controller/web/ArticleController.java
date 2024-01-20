package com.newswebsite.main.controller.web;

import com.newswebsite.main.dto.ArticleDTO;
import com.newswebsite.main.enums.ArticleState;
import com.newswebsite.main.enums.Role;
import com.newswebsite.main.security.SecurityUtil;
import com.newswebsite.main.service.IArticleReader;
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

    @Autowired
    private IArticleReader articleRetrievalService;

    @GetMapping
    public ModelAndView getDetails(@PathVariable("alias") String alias,
                                   @RequestParam(value = "previewMode", required = false) boolean previewMode) {
        String viewName = "web/details";
        List<String> roles = SecurityUtil.getAuthorities();

        ArticleDTO articleDTO = (previewMode && (roles.contains(Role.WRITER.name()) || roles.contains(Role.ADMIN.name()))) ?
                articleRetrievalService.findByAlias(alias) :
                articleRetrievalService.findByAliasAndStateCode(alias, ArticleState.PUBLISHED.name());

        ModelAndView view = new ModelAndView(viewName);
        view.addObject("article", articleDTO);
        return view;
    }
}
