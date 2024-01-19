package com.newswebsite.main.service;

import com.newswebsite.main.dto.ArticleDTO;

public interface IArticleState {
    void submit(ArticleDTO articleDTO);

    void approve(ArticleDTO articleDTO);

    void reject(ArticleDTO articleDTO);

    void publish(ArticleDTO articleDTO);

    void trash(ArticleDTO articleDTO);
}
