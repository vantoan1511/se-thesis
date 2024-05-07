package com.newswebsite.main.controller.admin;

import com.newswebsite.main.dto.ArticleDTO;
import com.newswebsite.main.enums.ArticleState;
import com.newswebsite.main.enums.Role;
import com.newswebsite.main.security.SecurityUtil;
import com.newswebsite.main.service.articleservice.IArticleReader;
import com.newswebsite.main.service.categoryservice.ICategoryReader;
import com.newswebsite.main.service.imageservice.IImageReader;
import com.newswebsite.main.service.stateservice.IStateReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    private final IArticleReader articleReader;

    private final ICategoryReader categoryReader;

    private final IStateReader stateReader;

    private final IImageReader imageReader;

    @Autowired
    public ArticleController(
            IArticleReader articleReader,
            ICategoryReader categoryReader,
            IStateReader stateReader,
            IImageReader imageReader
    ) {
        this.articleReader = articleReader;
        this.categoryReader = categoryReader;
        this.stateReader = stateReader;
        this.imageReader = imageReader;
    }

    @GetMapping
    public ModelAndView getListPage(@RequestParam(name = "tab", required = false, defaultValue = "all") String tab,
                                    @RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "limit", defaultValue = "10") int limit,
                                    @RequestParam(name = "by", defaultValue = "publishedAt") String by,
                                    @RequestParam(name = "order", defaultValue = "DESC") String order) {
        String viewName = "admin/listArticles";
        String username = SecurityUtil.username();
        List<String> authorities = SecurityUtil.getAuthorities();

        Sort.Direction direction = Sort.Direction.fromString(order);
        Pageable pageable = new PageRequest(page - 1, limit, direction, by);

        Page<?> contents = null;

        if (authorities.contains(Role.ADMIN.name())) {
            switch (tab) {
                case "all" -> contents = articleReader.getNotTrashArticles(pageable);
                case "published" -> contents = articleReader.getAllByStateCode(ArticleState.PUBLISHED.name(), pageable);
                case "pending" -> contents = articleReader.getAllByStateCode(ArticleState.PENDING.name(), pageable);
                case "trash" -> contents = articleReader.getAllByStateCode(ArticleState.TRASH.name(), pageable);
                case "featured" -> contents = articleReader.getAllByFeatured(true, pageable);
                default -> viewName = "admin/404";
            }
        } else {
            switch (tab) {
                case "all" -> contents = articleReader.getAllByAuthorAndStateCode(username, null, pageable);
                case "published" ->
                        contents = articleReader.getAllByAuthorAndStateCode(username, ArticleState.PUBLISHED.name(), pageable);
                case "pending" ->
                        contents = articleReader.getAllByAuthorAndStateCode(username, ArticleState.PENDING.name(), pageable);
                case "trash" ->
                        contents = articleReader.getAllByAuthorAndStateCode(username, ArticleState.TRASH.name(), pageable);
                case "featured" -> contents = articleReader.getAllByFeaturedAndAuthor(true, username, pageable);
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
        String viewName = previewMode ? "web/articleDetails" : "admin/articleDetails";
        List<String> roles = SecurityUtil.getAuthorities();

        ArticleDTO articleDTO = id == null ? new ArticleDTO() : articleReader.getById(id);

        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("article", articleDTO);
        mav.addObject("categories", categoryReader.getAllAsMap());
        /*mav.addObject("uploadedImages",
                imageReader.getFiles(SecurityUtil.username(), new PageRequest(0, 99, Sort.Direction.DESC, "createdAt")));*/
        return mav;
    }
}
