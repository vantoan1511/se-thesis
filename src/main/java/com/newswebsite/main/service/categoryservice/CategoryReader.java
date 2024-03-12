package com.newswebsite.main.service.categoryservice;

import com.newswebsite.main.dto.CategoryDTO;
import com.newswebsite.main.entity.Category;
import com.newswebsite.main.exception.CategoryNotFoundException;
import com.newswebsite.main.mapper.CollectionMapper;
import com.newswebsite.main.repository.CategoryRepo;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryReader implements ICategoryReader {

    private final CategoryRepo categoryRepo;

    private final MessageSource msg;

    private final CollectionMapper mapper;

    public CategoryReader(CategoryRepo categoryRepo, MessageSource msg, CollectionMapper mapper) {
        this.categoryRepo = categoryRepo;
        this.msg = msg;
        this.mapper = mapper;
    }

    @Override
    public CategoryDTO getCategory(String alias) {
        Category category = categoryRepo.findByAlias(alias);
        if (category == null)
            throw new CategoryNotFoundException(msg.getMessage("category.not.found", null, null) + alias);
        return mapper.map(category, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAll() {
        return mapper.map(categoryRepo.findAll(), CategoryDTO.class);
    }

    @Override
    public Page<CategoryDTO> getAll(Pageable pageable) {
        return categoryRepo.findAll(pageable).map(item -> mapper.map(item, CategoryDTO.class));
    }

    @Override
    public Map<String, String> getAllAsMap() {
        return mapper.map(categoryRepo.findAll(), Category::getAlias, Category::getTitle);
    }
}
