package com.newswebsite.main.service.impl;

import com.newswebsite.main.dto.ArticleDTO;
import com.newswebsite.main.entity.Article;
import com.newswebsite.main.enums.ArticleState;
import com.newswebsite.main.mapper.CollectionMapper;
import com.newswebsite.main.repository.ArticleRepo;
import com.newswebsite.main.service.IArticleRetrievalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleRetrievalService implements IArticleRetrievalService {
    private final ArticleRepo articleRepo;

    private final CollectionMapper mapper = new CollectionMapper();

    @Autowired
    public ArticleRetrievalService(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    @Override
    public Page<ArticleDTO> findAll(Pageable pageable) {
        return articleRepo.findAll(pageable)
                .map(item -> mapper.map(item, ArticleDTO.class));
    }

    @Override
    public Page<ArticleDTO> findAllByAuthorAndStateCode(String author, String stateCode, Pageable pageable) {
        Page<Article> contents = stateCode == null ?
                articleRepo.findAllByCreatedByAndStateCodeNot(author, ArticleState.TRASH.name(), pageable) :
                articleRepo.findAllByCreatedByAndStateCode(author, stateCode, pageable);
        return contents
                .map(item -> mapper.map(item, ArticleDTO.class));
    }

    @Override
    public Page<ArticleDTO> findAllByFeaturedAndAuthor(boolean featured, String author, Pageable pageable) {
        return articleRepo.findAllByFeaturedAndCreatedByAndStateCodeNot(featured, author, ArticleState.TRASH.name(), pageable)
                .map(item -> mapper.map(item, ArticleDTO.class));
    }

    @Override
    public ArticleDTO findById(long id) {
        return mapper.map(articleRepo.findOne(id), ArticleDTO.class);
    }
}
