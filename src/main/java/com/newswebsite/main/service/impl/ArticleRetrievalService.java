package com.newswebsite.main.service.impl;

import com.newswebsite.main.dto.ArticleDTO;
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

    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    public ArticleRetrievalService(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    @Override
    public Page<ArticleDTO> findAll(Pageable pageable) {
        return articleRepo.findAll(pageable)
                .map(item -> mapper.map(item, ArticleDTO.class));
    }
}
