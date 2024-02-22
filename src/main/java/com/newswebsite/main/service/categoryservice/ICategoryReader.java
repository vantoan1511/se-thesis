package com.newswebsite.main.service.categoryservice;

import com.newswebsite.main.dto.CategoryDTO;

import java.util.List;
import java.util.Map;

public interface ICategoryReader {
    CategoryDTO getCategory(String alias);

    List<CategoryDTO> getAll();

    Map<String, String> getAllAsMap();
}
