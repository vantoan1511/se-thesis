package com.newswebsite.main.controller.admin;

import com.newswebsite.main.dto.ArticleDTO;
import com.newswebsite.main.enums.ArticleState;
import com.newswebsite.main.enums.Role;
import com.newswebsite.main.security.SecurityUtil;
import com.newswebsite.main.service.IArticleRetrievalService;
import com.newswebsite.main.service.ICategoryService;
import com.newswebsite.main.service.IStateService;
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
    private IArticleRetrievalService articleRetrievalService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IStateService stateService;

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
            contents = articleRetrievalService.findAll(pageable);
        } else {
            switch (tab) {
                case "all" -> contents = articleRetrievalService.findAllByAuthorAndStateCode(username, null, pageable);
                case "published" ->
                        contents = articleRetrievalService.findAllByAuthorAndStateCode(username, ArticleState.PUBLISHED.name(), pageable);
                case "pending" ->
                        contents = articleRetrievalService.findAllByAuthorAndStateCode(username, ArticleState.PENDING.name(), pageable);
                case "trash" ->
                        contents = articleRetrievalService.findAllByAuthorAndStateCode(username, ArticleState.TRASH.name(), pageable);
                case "featured" ->
                        contents = articleRetrievalService.findAllByFeaturedAndAuthor(true, username, pageable);
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
    public ModelAndView modifyArticle(@PathVariable(name = "id", required = false) Long id) {
        List<String> roles = SecurityUtil.getAuthorities();

        ArticleDTO articleDTO = id == null ? new ArticleDTO() : articleRetrievalService.findById(id);

        String viewName = "admin/article/details";

        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("model", articleDTO);
        mav.addObject("categories", categoryService.findAll());
        /*mav.addObject("access", accessService.findAll());*/
        mav.addObject("states", stateService.findAll());
        return mav;
    }
}
