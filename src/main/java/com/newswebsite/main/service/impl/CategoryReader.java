package com.newswebsite.main.service.impl;

import com.newswebsite.main.dto.CategoryDTO;
import com.newswebsite.main.entity.Category;
import com.newswebsite.main.exception.CategoryCodeNotFoundException;
import com.newswebsite.main.mapper.CollectionMapper;
import com.newswebsite.main.repository.CategoryRepo;
import com.newswebsite.main.service.ICategoryReader;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryReader implements ICategoryReader {

    private final CategoryRepo categoryRepo;

    private final MessageSource msg;

    private final CollectionMapper mapper = new CollectionMapper();

    public CategoryReader(CategoryRepo categoryRepo, MessageSource msg) {
        this.categoryRepo = categoryRepo;
        this.msg = msg;
    }

    @Override
    public CategoryDTO findByCode(String code) {
        Category category = categoryRepo.findByCode(code);
        if (category == null)
            throw new CategoryCodeNotFoundException(msg.getMessage("category.not.found", null, null) + code);
        return mapper.map(category, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getCategories() {
        return mapper.map(categoryRepo.findAll(), CategoryDTO.class);
    }

    @Override
    public Map<String, String> getCategoriesMap() {
        return mapper.map(categoryRepo.findAll(), Category::getCode, Category::getName);
    }
}
