package com.newswebsite.main.service.categoryservice;

import com.newswebsite.main.dto.CategoryDTO;
import com.newswebsite.main.dto.request.CategoryCreationRequest;

import java.util.List;

public interface ICategoryWriter {
    CategoryDTO save(CategoryCreationRequest categoryCreationRequest);

    void delete(long id);

    void deleteMultiple(List<Long> ids);
}
