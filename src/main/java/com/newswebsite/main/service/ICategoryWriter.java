package com.newswebsite.main.service;

import com.newswebsite.main.dto.CategoryDTO;

public interface ICategoryWriter {
    CategoryDTO save(CategoryDTO categoryDTO);
}
