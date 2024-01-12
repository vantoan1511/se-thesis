package com.newswebsite.main.service.impl;

import com.newswebsite.main.dto.ArticleDTO;
import com.newswebsite.main.entity.Article;
import com.newswebsite.main.repository.ArticleRepo;
import com.newswebsite.main.service.IArticleModificationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ArticleModificationService implements IArticleModificationService {

    private final ArticleRepo articleRepo;

    private final ModelMapper mapper = new ModelMapper();

    public ArticleModificationService(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    @Override
    public ArticleDTO save(ArticleDTO articleDTO) {
        Article article = mapper.map(articleDTO, Article.class);
        return mapper.map(articleRepo.save(article), ArticleDTO.class);
    }
}
