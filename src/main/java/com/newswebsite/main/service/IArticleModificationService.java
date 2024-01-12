package com.newswebsite.main.service;

import com.newswebsite.main.dto.ArticleDTO;

public interface IArticleModificationService {
    ArticleDTO save(ArticleDTO articleDTO);
}
