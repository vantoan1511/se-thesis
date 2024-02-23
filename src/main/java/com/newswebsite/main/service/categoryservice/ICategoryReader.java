package com.newswebsite.main.service.categoryservice;

import com.newswebsite.main.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ICategoryReader {
    CategoryDTO getCategory(String alias);

    List<CategoryDTO> getAll();

    Page<CategoryDTO> getAll(Pageable pageable);

    Map<String, String> getAllAsMap();
}
