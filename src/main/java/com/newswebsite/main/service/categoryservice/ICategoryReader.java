package com.newswebsite.main.service.categoryservice;

import com.newswebsite.main.dto.CategoryDTO;

import java.util.List;
import java.util.Map;

public interface ICategoryReader {
    CategoryDTO findByCode(String code);

    List<CategoryDTO> getCategories();

    Map<String, String> getCategoriesMap();
}
