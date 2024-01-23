package com.newswebsite.main.service.stateservice;

import com.newswebsite.main.entity.Article;
import com.newswebsite.main.enums.ArticleState;
import com.newswebsite.main.repository.StateRepo;
import com.newswebsite.main.service.stateservice.IStateService;

public class TrashState extends IStateService {
    public TrashState(StateRepo stateRepo) {
        super(stateRepo);
    }

    public TrashState(StateRepo stateRepo, String currentState) {
        super(stateRepo, currentState);
    }

    @Override
    public void restore(Article article) {
        article.setState(stateRepo.findByCode(ArticleState.DRAFT.name()));
    }
}
