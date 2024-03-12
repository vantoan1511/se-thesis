package com.newswebsite.main.mapper;

import com.newswebsite.main.dto.request.ArticleCreationRequest;
import com.newswebsite.main.entity.Article;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    Article toArticle(ArticleCreationRequest articleCreationRequest);
}
