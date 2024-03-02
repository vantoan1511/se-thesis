package com.newswebsite.main.controller.admin;

import com.newswebsite.main.dto.CategoryDTO;
import com.newswebsite.main.service.categoryservice.ICategoryReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    private final ICategoryReader categoryReader;

    @Autowired
    public CategoryController(ICategoryReader categoryReader) {
        this.categoryReader = categoryReader;
    }

    @GetMapping
    public ModelAndView getList(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            @RequestParam(name = "by", defaultValue = "lastModifiedAt") String by,
            @RequestParam(name = "order", defaultValue = "DESC") String order) {
        String viewName = "admin/listCategories";

        Pageable pageable = new PageRequest(page - 1, size, Sort.Direction.fromString(order), by);

        ModelAndView view = new ModelAndView(viewName);
        view.addObject("pagedCategories", categoryReader.getAll(pageable));
        view.addObject("sortBy", by);
        view.addObject("sortOrder", order);
        return view;
    }

    @GetMapping({"/new", "/{categoryAlias}"})
    public ModelAndView createOrUpdate(@PathVariable(value = "categoryAlias", required = false) String categoryAlias) {
        String viewName = "admin/categoryDetails";

        CategoryDTO categoryDTO = categoryAlias != null ? categoryReader.getCategory(categoryAlias) : new CategoryDTO();

        ModelAndView view = new ModelAndView(viewName);
        view.addObject("category", categoryDTO);
        view.addObject("categories", categoryReader.getAllAsMap());
        return view;
    }
}
