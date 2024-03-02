package com.newswebsite.main.service.categoryservice;

import com.newswebsite.main.dto.CategoryDTO;
import com.newswebsite.main.entity.Category;
import com.newswebsite.main.exception.CategoryCodeNotFoundException;
import com.newswebsite.main.mapper.CollectionMapper;
import com.newswebsite.main.repository.CategoryRepo;
import com.newswebsite.main.service.categoryservice.ICategoryWriter;
import com.newswebsite.main.utils.SlugGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CategoryWriter implements ICategoryWriter {

    private final CategoryRepo categoryRepo;

    private final CollectionMapper mapper;

    @Autowired
    public CategoryWriter(CategoryRepo categoryRepo, CollectionMapper mapper) {
        this.categoryRepo = categoryRepo;
        this.mapper = mapper;
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        //Category category = mapper.map(categoryDTO, Category.class);
        Category category = new Category();
        category.setTitle(categoryDTO.getTitle());
        category.setAlias(categoryDTO.getAlias());
        category.setDescription(categoryDTO.getDescription());
        category.setPublishedAt(new Date());

        Category oldCategory = categoryDTO.getId() != null ? categoryRepo.findOne(categoryDTO.getId()) : new Category();
        Category parent = categoryDTO.getParentAlias().isBlank() ? null : categoryRepo.findByAlias(categoryDTO.getParentAlias());

        if (oldCategory != null) {
            category.setCreatedAt(oldCategory.getCreatedAt());
            category.setCreatedBy(oldCategory.getCreatedBy());
            category.setPublishedAt(oldCategory.getPublishedAt());
        }

        String newAlias = StringUtils.hasText(category.getAlias()) ? category.getAlias() : category.getTitle();
        newAlias = SlugGenerator.slugify.slugify(newAlias);
        String oldAlias = oldCategory.getAlias();
        if (!newAlias.equals(oldAlias) && !isUniqueAlias(newAlias)) {
            newAlias = SlugGenerator.generateUniqueSlug(newAlias);
        }

        category.setAlias(newAlias);
        category.setParent(parent);

        category = categoryRepo.save(category);
        return mapper.map(category, CategoryDTO.class);
    }

    @Override
    public void delete(long id) {
        if (!categoryRepo.exists(id)) {
            throw new CategoryCodeNotFoundException("Chuyên mục không tồn tại " + id);
        }
        categoryRepo.delete(id);
    }

    @Override
    public void deleteMultiple(List<Long> ids) {
        for (long id : ids) {
            delete(id);
        }
    }

    private boolean isUniqueAlias(String alias) {
        return categoryRepo.findByAlias(alias) == null;
    }
}
