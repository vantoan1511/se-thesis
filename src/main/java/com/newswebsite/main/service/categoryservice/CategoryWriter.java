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
public class CategoryWriter implements ICategoryWriter {

    @Autowired
    private CategoryRepo categoryRepo;

    private final CollectionMapper mapper = new CollectionMapper();

    @Override
    @Transactional
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = mapper.map(categoryDTO, Category.class);
        Category oldCategory = categoryDTO.getId() != null ? categoryRepo.findOne(categoryDTO.getId()) : new Category();
        Category parent = categoryDTO.getParentAlias().isBlank() ? null : categoryRepo.findByAlias(categoryDTO.getParentAlias());

        if (oldCategory != null) {
            category.setCreatedAt(oldCategory.getCreatedAt());
            category.setCreatedBy(oldCategory.getCreatedBy());
            category.setPublishedAt(oldCategory.getPublishedAt());
        }

        String newAlias = StringUtils.hasText(category.getAlias()) ? category.getAlias() : category.getTitle();
        String oldAlias = oldCategory != null ? oldCategory.getAlias() : null;
        if (!newAlias.equals(oldAlias) && !isUniqueAlias(newAlias)) {
            newAlias = SlugGenerator.generateUniqueSlug(newAlias);
        } else {
            newAlias = SlugGenerator.slugify.slugify(newAlias);
        }

        category.setAlias(newAlias);
        category.setPublishedAt(new Date());
        category.setParent(parent);

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

    private boolean isUniqueAlias(String alias) {
        return categoryRepo.findByAlias(alias) == null;
    }
}
