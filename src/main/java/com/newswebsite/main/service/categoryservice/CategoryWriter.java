package com.newswebsite.main.service.categoryservice;

import com.newswebsite.main.dto.CategoryDTO;
import com.newswebsite.main.dto.request.CategoryCreationRequest;
import com.newswebsite.main.entity.Category;
import com.newswebsite.main.exception.CategoryNotFoundException;
import com.newswebsite.main.mapper.CategoryMapper;
import com.newswebsite.main.mapper.CollectionMapper;
import com.newswebsite.main.repository.CategoryRepo;
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

    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryWriter(
            CategoryRepo categoryRepo,
            CollectionMapper mapper, CategoryMapper categoryMapper
    ) {
        this.categoryRepo = categoryRepo;
        this.mapper = mapper;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDTO save(CategoryCreationRequest categoryCreationRequest) {
        Category category = categoryMapper.toCategory(categoryCreationRequest);

        if (StringUtils.hasText(categoryCreationRequest.getParentAlias())) {
            Category parent = categoryRepo.findByAlias(categoryCreationRequest.getParentAlias());
            category.setParent(parent);
        }

        String newAlias = SlugGenerator.slugify.slugify(StringUtils.hasText(category.getAlias()) ? category.getAlias() : category.getTitle());

        if (categoryCreationRequest.getId() != null) {
            Category oldCategory = categoryRepo.findOne(categoryCreationRequest.getId());
            if (oldCategory == null)
                throw new CategoryNotFoundException("Không tìm thấy chuyên mục với id [%s]".formatted(categoryCreationRequest.getId()));
            String oldAlias = oldCategory.getAlias();
            if (!newAlias.equals(oldAlias) && !isUniqueAlias(newAlias)) {
                newAlias = SlugGenerator.generateUniqueSlug(newAlias);
            }
            category.setPublishedAt(oldCategory.getPublishedAt());
            category.setCreatedBy(oldCategory.getCreatedBy());
            category.setCreatedAt(oldCategory.getCreatedAt());
        } else {
            category.setPublishedAt(new Date());
        }

        category.setAlias(newAlias);

        category = categoryRepo.save(category);
        return mapper.map(category, CategoryDTO.class);
    }

    @Override
    public void delete(long id) {
        if (!categoryRepo.exists(id)) {
            throw new CategoryNotFoundException("Chuyên mục không tồn tại " + id);
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
