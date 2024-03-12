package com.newswebsite.main.mapper;

import com.newswebsite.main.dto.request.CategoryCreationRequest;
import com.newswebsite.main.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(CategoryCreationRequest categoryCreationRequest);
}
