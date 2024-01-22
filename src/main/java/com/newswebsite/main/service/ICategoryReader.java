package com.newswebsite.main.service;

import com.newswebsite.main.dto.CategoryDTO;

import java.util.Map;

public interface ICategoryReader {
    CategoryDTO findByCode(String code);

    Map<String, String> getCategoriesMap();
}
