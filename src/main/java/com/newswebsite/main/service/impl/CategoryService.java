package com.newswebsite.main.service.impl;

import com.newswebsite.main.dto.CategoryDTO;
import com.newswebsite.main.entity.Category;
import com.newswebsite.main.exception.CategoryCodeNotFoundException;
import com.newswebsite.main.mapper.CollectionMapper;
import com.newswebsite.main.repository.CategoryRepo;
import com.newswebsite.main.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepo categoryRepo;

    private final MessageSource msg;

    private final CollectionMapper mapper = new CollectionMapper();

    public CategoryService(CategoryRepo categoryRepo, MessageSource msg) {
        this.categoryRepo = categoryRepo;
        this.msg = msg;
    }

    @Override
    public CategoryDTO findByCode(String code) {
        Category category = categoryRepo.findByCategoryCode(code);
        if (category == null)
            throw new CategoryCodeNotFoundException(msg.getMessage("category.not.found", null, null) + code);
        return mapper.map(category, CategoryDTO.class);
    }

    @Override
    public Map<String, String> findAll() {
        return mapper.map(categoryRepo.findAll(), Category::getCategoryCode, Category::getCategoryName);
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = mapper.map(categoryDTO, Category.class);
        return mapper.map(categoryRepo.save(category), CategoryDTO.class);
    }
}
