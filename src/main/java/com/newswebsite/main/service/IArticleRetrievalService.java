package com.newswebsite.main.service;

import com.newswebsite.main.dto.ArticleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IArticleRetrievalService {
    Page<ArticleDTO> findAll(Pageable pageable);

    Page<ArticleDTO> findAllByAuthorAndStateCode(String author, String stateCode, Pageable pageable);

    Page<ArticleDTO> findAllByFeaturedAndAuthor(boolean featured, String author, Pageable pageable);

    ArticleDTO findById(long id);
}
