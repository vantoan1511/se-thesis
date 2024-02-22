package com.newswebsite.main.api.web;

import com.newswebsite.main.dto.CategoryDTO;
import com.newswebsite.main.service.categoryservice.ICategoryReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "webCategoryAPI")
@RequestMapping("/public/api/v1/categories")
public class CategoryAPI {

    private final ICategoryReader categoryReader;

    @Autowired
    public CategoryAPI(ICategoryReader categoryReader) {
        this.categoryReader = categoryReader;
    }

    @GetMapping
    List<CategoryDTO> getAll() {
        return categoryReader.getAll();
    }
}
