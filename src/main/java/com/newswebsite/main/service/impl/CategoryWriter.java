package com.newswebsite.main.service.impl;

import com.newswebsite.main.dto.CategoryDTO;
import com.newswebsite.main.entity.Category;
import com.newswebsite.main.mapper.CollectionMapper;
import com.newswebsite.main.repository.CategoryRepo;
import com.newswebsite.main.service.ICategoryWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryWriter implements ICategoryWriter {

    @Autowired
    private CategoryRepo categoryRepo;

    private final CollectionMapper mapper = new CollectionMapper();

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = mapper.map(categoryDTO, Category.class);
        return mapper.map(categoryRepo.save(category), CategoryDTO.class);
    }
}
