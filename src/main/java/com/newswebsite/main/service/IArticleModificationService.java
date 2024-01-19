package com.newswebsite.main.service;

import com.newswebsite.main.dto.ArticleDTO;
import com.newswebsite.main.enums.ArticleState;

import java.util.List;

public interface IArticleModificationService {

    void submit(long id);

    void trash(long id);

    void approve(long id);

    void reject(long id);

    void publish(long id);

    void edit(long id);

    void unPublish(long id);

    void restore(long id);

    void approveMultiple(List<Long> ids);

    void rejectMultiple(List<Long> ids);

    void trashMultiple(List<Long> ids);

    void restoreMultiple(List<Long> ids);

    ArticleDTO save(ArticleDTO articleDTO);

    void delete(Long id);

    void deleteArticles(List<Long> ids);
}
