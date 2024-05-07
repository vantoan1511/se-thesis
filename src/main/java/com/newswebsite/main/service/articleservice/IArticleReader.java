package com.newswebsite.main.service.articleservice;

import com.newswebsite.main.dto.ArticleDTO;
import com.newswebsite.main.dto.response.ArticleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface IArticleReader {
    ArticleDTO getById(long id);

    ArticleResponse getByAlias(String alias);

    ArticleResponse getPublishedArticle(String alias);

    Page<ArticleDTO> search(String q, List<Long> categoryIds, Date startDate, Pageable pageable);

    Page<ArticleResponse> getLatestArticles(Pageable pageable);

    Page<ArticleDTO> getAll(Pageable pageable);

    Page<ArticleResponse> getFeaturedArticles(Pageable pageable);

    Page<ArticleDTO> getPendingArticles(Pageable pageable);

    Page<ArticleResponse> getAllPublishedArticles(Pageable pageable);

    Page<ArticleDTO> getAllPublishedByCategory(String categoryAlias, Pageable pageable);

    Page<ArticleDTO> getTrashArticles(Pageable pageable);

    Page<ArticleDTO> getNotTrashArticles(Pageable pageable);

    Page<ArticleDTO> getAllByAuthorAndStateCode(String author, String stateCode, Pageable pageable);

    Page<ArticleDTO> getAllByFeaturedAndAuthor(boolean featured, String author, Pageable pageable);

    Page<ArticleResponse> getAllByStateCode(String stateCode, Pageable pageable);

    Page<ArticleResponse> getAllByFeatured(boolean featured, Pageable pageable);

    long countTotalArticles();
}
