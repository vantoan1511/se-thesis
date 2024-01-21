package com.newswebsite.main.service.impl;

import com.newswebsite.main.entity.Article;
import com.newswebsite.main.enums.ArticleState;
import com.newswebsite.main.repository.StateRepo;
import com.newswebsite.main.service.IStateService;

public class DraftState extends IStateService {
    public DraftState(StateRepo stateRepo) {
        super(stateRepo);
    }

    public DraftState(StateRepo stateRepo, String currentState) {
        super(stateRepo, currentState);
    }

    @Override
    public void submit(Article article) {
        article.setState(stateRepo.findByCode(ArticleState.PENDING.name()));
    }

    @Override
    public void toTrash(Article article) {
        article.setState(stateRepo.findByCode(ArticleState.TRASH.name()));
    }
}
