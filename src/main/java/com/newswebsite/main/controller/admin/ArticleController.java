package com.newswebsite.main.controller.admin;

import com.newswebsite.main.dto.ArticleDTO;
import com.newswebsite.main.enums.ArticleState;
import com.newswebsite.main.enums.Role;
import com.newswebsite.main.security.SecurityUtil;
import com.newswebsite.main.service.articleservice.IArticleReader;
import com.newswebsite.main.service.categoryservice.ICategoryReader;
import com.newswebsite.main.service.stateservice.IStateReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/articles")
public class ArticleController {

    @Autowired
    private IArticleReader articleReader;

    @Autowired
    private ICategoryReader categoryReader;

    @Autowired
    private IStateReader stateReader;

    @GetMapping
    public ModelAndView getListPage(@RequestParam(name = "tab", required = false, defaultValue = "all") String tab,
                                    @RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "limit", defaultValue = "10") int limit,
                                    @RequestParam(name = "by", defaultValue = "lastModifiedAt") String by,
                                    @RequestParam(name = "order", defaultValue = "DESC") String order) {
        String viewName = "admin/article/list";
        String username = SecurityUtil.username();
        List<String> authorities = SecurityUtil.getAuthorities();

        Sort.Direction direction = Sort.Direction.fromString(order);
        Pageable pageable = new PageRequest(page - 1, limit, new Sort(direction, by));

        Page<ArticleDTO> contents = null;

        if (authorities.contains(Role.ADMIN.name())) {
            switch (tab) {
                case "all" -> contents = articleReader.getNotTrashArticles(pageable);
                case "published" -> contents = articleReader.getPublishedArticles(pageable);
                case "pending" -> contents = articleReader.getPendingArticles(pageable);
                case "trash" -> contents = articleReader.getTrashArticles(pageable);
                case "featured" -> contents = articleReader.getFeaturedArticles(pageable);
                default -> viewName = "admin/404";
            }
        } else {
            switch (tab) {
                case "all" -> contents = articleReader.findAllByAuthorAndStateCode(username, null, pageable);
                case "published" ->
                        contents = articleReader.findAllByAuthorAndStateCode(username, ArticleState.PUBLISHED.name(), pageable);
                case "pending" ->
                        contents = articleReader.findAllByAuthorAndStateCode(username, ArticleState.PENDING.name(), pageable);
                case "trash" ->
                        contents = articleReader.findAllByAuthorAndStateCode(username, ArticleState.TRASH.name(), pageable);
                case "featured" -> contents = articleReader.findAllByFeaturedAndAuthor(true, username, pageable);
                default -> viewName = "admin/404";
            }
        }

        ModelAndView view = new ModelAndView(viewName);
        view.addObject("model", contents);
        view.addObject("sortBy", by);
        view.addObject("sortOrder", order);
        return view;
    }

    @GetMapping({"/new", "/{id}"})
    public ModelAndView modifyArticle(@PathVariable(name = "id", required = false) Long id,
                                      @RequestParam(value = "previewMode", required = false) boolean previewMode) {
        String viewName = previewMode ? "web/details" : "admin/article/details";
        List<String> roles = SecurityUtil.getAuthorities();

        ArticleDTO articleDTO = id == null ? new ArticleDTO() : articleReader.findById(id);

        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("article", articleDTO);
        mav.addObject("categories", categoryReader.getCategoriesMap());
        mav.addObject("states", stateReader.findAll());
        return mav;
    }
}
