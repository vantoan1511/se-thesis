package com.newswebsite.main.service.articleservice;

import com.newswebsite.main.dto.ArticleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface IArticleReader {
    ArticleDTO findById(long id);

    ArticleDTO findByAlias(String alias);

    ArticleDTO getPublishedArticle(String alias);

    Page<ArticleDTO> search(String q, List<Long> categoryIds, Date startDate, Pageable pageable);

    Page<ArticleDTO> getLatestArticles(Pageable pageable);

    Page<ArticleDTO> findAll(Pageable pageable);

    Page<ArticleDTO> getFeaturedArticles(Pageable pageable);

    Page<ArticleDTO> getPendingArticles(Pageable pageable);

    Page<ArticleDTO> getPublishedArticle(Pageable pageable);

    Page<ArticleDTO> getTrashArticles(Pageable pageable);

    Page<ArticleDTO> getNotTrashArticles(Pageable pageable);

    Page<ArticleDTO> findAllByAuthorAndStateCode(String author, String stateCode, Pageable pageable);

    Page<ArticleDTO> findAllByFeaturedAndAuthor(boolean featured, String author, Pageable pageable);

}
