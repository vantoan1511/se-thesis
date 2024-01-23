package com.newswebsite.main.service;

import com.newswebsite.main.dto.CategoryDTO;

import java.util.List;

public interface ICategoryWriter {
    CategoryDTO save(CategoryDTO categoryDTO);

    void delete(long id);

    void deleteMultiple(List<Long> ids);
}
