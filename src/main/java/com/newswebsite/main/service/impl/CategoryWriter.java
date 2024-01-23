package com.newswebsite.main.service.impl;

import com.newswebsite.main.dto.CategoryDTO;
import com.newswebsite.main.entity.Category;
import com.newswebsite.main.exception.CategoryCodeNotFoundException;
import com.newswebsite.main.mapper.CollectionMapper;
import com.newswebsite.main.repository.CategoryRepo;
import com.newswebsite.main.service.ICategoryWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class CategoryWriter implements ICategoryWriter {

    @Autowired
    private CategoryRepo categoryRepo;

    private final CollectionMapper mapper = new CollectionMapper();

    @Override
    @Transactional
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = mapper.map(categoryDTO, Category.class);
        category.setPublishedAt(new Date());
        if (categoryDTO.getId() != null) {
            Category oldCategory = categoryRepo.findOne(categoryDTO.getId());
            category.setCreatedAt(oldCategory.getCreatedAt());
            category.setCreatedBy(oldCategory.getCreatedBy());
            category.setPublishedAt(oldCategory.getPublishedAt());
        }
        category = categoryRepo.save(category);
        return mapper.map(category, CategoryDTO.class);
    }

    @Override
    @Transactional
    public void delete(long id) {
        if (!categoryRepo.exists(id)) {
            throw new CategoryCodeNotFoundException("Chuyên mục không tồn tại " + id);
        }
        categoryRepo.delete(id);
    }

    @Override
    @Transactional
    public void deleteMultiple(List<Long> ids) {
        for (long id : ids) {
            delete(id);
        }
    }
}
