package com.newswebsite.main.service;

import com.newswebsite.main.dto.ArticleDTO;
import com.newswebsite.main.enums.ArticleState;

import java.util.List;

public interface IArticleModificationService {
    ArticleDTO save(ArticleDTO articleDTO);

    void changeState(ArticleState state, long id);

    void delete(Long id);

    void deleteArticles(List<Long> ids);
}
