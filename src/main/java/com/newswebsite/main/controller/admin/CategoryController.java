package com.newswebsite.main.controller.admin;

import com.newswebsite.main.dto.CategoryDTO;
import com.newswebsite.main.service.ICategoryReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    @Autowired
    private ICategoryReader categoryReader;

    @GetMapping
    public ModelAndView getList(@RequestParam(name = "tab", required = false, defaultValue = "all") String tab,
                                @RequestParam(name = "page", defaultValue = "1") int page,
                                @RequestParam(name = "limit", defaultValue = "10") int limit,
                                @RequestParam(name = "by", defaultValue = "lastModifiedAt") String by,
                                @RequestParam(name = "order", defaultValue = "DESC") String order) {
        String viewName = "admin/category/categories";

        Sort.Direction direction = Sort.Direction.fromString(order);
        Pageable pageable = new PageRequest(page - 1, limit, new Sort(direction, by));

        Page<CategoryDTO> contents;

        ModelAndView view = new ModelAndView(viewName);
        view.addObject("model", categoryReader.getCategoriesMap());
        view.addObject("sortBy", by);
        view.addObject("sortOrder", order);
        return view;
    }
}
