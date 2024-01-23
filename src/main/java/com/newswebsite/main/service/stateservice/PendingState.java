package com.newswebsite.main.service.stateservice;

import com.newswebsite.main.entity.Article;
import com.newswebsite.main.enums.ArticleState;
import com.newswebsite.main.repository.StateRepo;
import com.newswebsite.main.service.stateservice.IStateService;

public class PendingState extends IStateService {
    public PendingState(StateRepo stateRepo) {
        super(stateRepo);
    }

    public PendingState(StateRepo stateRepo, String currentState) {
        super(stateRepo, currentState);
    }

    @Override
    public void approve(Article article) {
        article.setState(stateRepo.findByCode(ArticleState.APPROVED.name()));
    }

    @Override
    public void reject(Article article) {
        article.setState(stateRepo.findByCode(ArticleState.DRAFT.name()));
    }
}
