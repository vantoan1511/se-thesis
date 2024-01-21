package com.newswebsite.main.service.impl;

import com.newswebsite.main.entity.Article;
import com.newswebsite.main.enums.ArticleState;
import com.newswebsite.main.repository.StateRepo;
import com.newswebsite.main.service.IStateService;

public class PublishedState extends IStateService {
    public PublishedState(StateRepo stateRepo) {
        super(stateRepo);
    }

    public PublishedState(StateRepo stateRepo, String currentState) {
        super(stateRepo, currentState);
    }

    @Override
    public void unPublish(Article article) {
        article.setState(stateRepo.findByCode(ArticleState.UNPUBLISHED.name()));
    }

    @Override
    public void edit(Article article) {
        article.setState(stateRepo.findByCode(ArticleState.DRAFT.name()));
    }
}
