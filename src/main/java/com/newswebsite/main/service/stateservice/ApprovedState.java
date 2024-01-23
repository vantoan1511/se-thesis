package com.newswebsite.main.service.stateservice;

import com.newswebsite.main.entity.Article;
import com.newswebsite.main.enums.ArticleState;
import com.newswebsite.main.repository.StateRepo;
import com.newswebsite.main.service.stateservice.IStateService;

public class ApprovedState extends IStateService {
    public ApprovedState(StateRepo stateRepo) {
        super(stateRepo);
    }

    public ApprovedState(StateRepo stateRepo, String currentState) {
        super(stateRepo, currentState);
    }

    @Override
    public void publish(Article article) {
        article.setState(stateRepo.findByCode(ArticleState.PUBLISHED.name()));
    }

    @Override
    public void toTrash(Article article) {
        article.setState(stateRepo.findByCode(ArticleState.TRASH.name()));
    }

    @Override
    public void edit(Article article) {
        article.setState(stateRepo.findByCode(ArticleState.DRAFT.name()));
    }
}
