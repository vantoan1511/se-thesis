package com.newswebsite.main.service.impl;

import com.newswebsite.main.dto.ArticleDTO;
import com.newswebsite.main.entity.Article;
import com.newswebsite.main.enums.ArticleState;
import com.newswebsite.main.exception.ArticleNotFoundException;
import com.newswebsite.main.mapper.CollectionMapper;
import com.newswebsite.main.repository.ArticleRepo;
import com.newswebsite.main.service.IArticleRetrievalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleRetrievalService implements IArticleRetrievalService {
    private final ArticleRepo articleRepo;

    private final MessageSource msg;

    private final CollectionMapper mapper = new CollectionMapper();

    @Autowired
    public ArticleRetrievalService(ArticleRepo articleRepo, MessageSource msg) {
        this.articleRepo = articleRepo;
        this.msg = msg;
    }

    @Override
    public ArticleDTO findById(long id) {
        Article article = articleRepo.findOne(id);
        if (article == null) throw new ArticleNotFoundException(msg.getMessage("article.not.found", null, null) + id);
        return mapper.map(article, ArticleDTO.class);
    }

    @Override
    public ArticleDTO findByAlias(String alias) {
        Article article = articleRepo.findByAlias(alias);
        if (article == null)
            throw new ArticleNotFoundException(msg.getMessage("article.not.found", null, null) + alias);
        return mapper.map(article, ArticleDTO.class);
    }

    @Override
    public ArticleDTO findByAliasAndStateCode(String alias, String stateCode) {
        Article article = articleRepo.findByAliasAndStateCode(alias, stateCode);
        if (article == null)
            throw new ArticleNotFoundException(msg.getMessage("article.not.found", null, null) + alias);
        return mapper.map(article, ArticleDTO.class);
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
}
