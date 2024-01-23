package com.newswebsite.main.service.stateservice;

import com.newswebsite.main.entity.Article;
import com.newswebsite.main.exception.InvalidArticleOperationException;
import com.newswebsite.main.repository.StateRepo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class IStateService {
    protected StateRepo stateRepo;

    private String currentState;

    public IStateService(StateRepo stateRepo) {
        this.stateRepo = stateRepo;
    }

    public IStateService(StateRepo stateRepo, String currentState) {
        this.stateRepo = stateRepo;
        this.currentState = currentState;
    }

    public void submit(Article article) {
        throw new InvalidArticleOperationException("Cannot submit the article that has current state is " + currentState);
    }

    public void approve(Article article) {
        throw new InvalidArticleOperationException("Cannot approve the article that has current state is " + currentState);
    }

    public void reject(Article article) {
        throw new InvalidArticleOperationException("Cannot reject the article that has current state is " + currentState);
    }

    public void publish(Article article) {
        throw new InvalidArticleOperationException("Cannot publish the article that has current state is " + currentState);
    }

    public void unPublish(Article article) {
        throw new InvalidArticleOperationException("Cannot unpublish the article that has current state is " + currentState);
    }

    public void toTrash(Article article) {
        throw new InvalidArticleOperationException("Cannot move to trash the article that has current state is " + currentState);
    }

    public void edit(Article article) {
        throw new InvalidArticleOperationException("Cannot edit the article that has current state is " + currentState);
    }

    public void restore(Article article) {
        throw new InvalidArticleOperationException("Cannot restore the article that has current state is " + currentState);
    }

}
