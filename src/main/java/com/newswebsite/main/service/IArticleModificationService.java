package com.newswebsite.main.service;

import com.newswebsite.main.dto.ArticleDTO;

import java.util.List;

public interface IArticleModificationService {
    ArticleDTO save(ArticleDTO articleDTO);

    void submitArticle(long id);

    void delete(Long id);

    void deleteArticles(List<Long> ids);
}
