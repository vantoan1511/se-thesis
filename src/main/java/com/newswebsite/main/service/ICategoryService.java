package com.newswebsite.main.service;

import com.newswebsite.main.dto.CategoryDTO;

import java.util.Map;

public interface ICategoryService {
    CategoryDTO findByCode(String code);

    Map<String, String> findAll();

    CategoryDTO save(CategoryDTO categoryDTO);
}
