package com.newswebsite.main.service.articleservice;

import com.newswebsite.main.dto.ArticleDTO;
import com.newswebsite.main.dto.request.ArticleCreationRequest;

import java.util.List;

public interface IArticleWriter {

    void increaseTraffic(String alias);

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

    void setFeatured(long id, boolean featured);

    void setFeaturedMultiple(List<Long> ids, boolean featured);

    ArticleDTO save(ArticleCreationRequest articleCreationRequest);

    void delete(Long id);

    void deleteArticles(List<Long> ids);
}
