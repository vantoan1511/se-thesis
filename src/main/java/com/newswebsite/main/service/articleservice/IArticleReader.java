package com.newswebsite.main.service.articleservice;

import com.newswebsite.main.dto.ArticleDTO;
import com.newswebsite.main.dto.response.FeaturedArticleResponse;
import com.newswebsite.main.dto.response.LatestArticleResponse;
import com.newswebsite.main.dto.response.PopularArticleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface IArticleReader {
    ArticleDTO getById(long id);

    ArticleDTO getByAlias(String alias);

    ArticleDTO getPublished(String alias);

    Page<ArticleDTO> search(String q, List<Long> categoryIds, Date startDate, Pageable pageable);

    Page<LatestArticleResponse> getLatestArticles(Pageable pageable);

    Page<ArticleDTO> getAll(Pageable pageable);

    Page<FeaturedArticleResponse> getFeaturedArticles(Pageable pageable);

    Page<ArticleDTO> getPendingArticles(Pageable pageable);

    Page<PopularArticleResponse> getAllPublished(Pageable pageable);

    Page<ArticleDTO> getAllPublishedByCategory(String categoryAlias, Pageable pageable);

    Page<ArticleDTO> getTrashArticles(Pageable pageable);

    Page<ArticleDTO> getNotTrashArticles(Pageable pageable);

    Page<ArticleDTO> getAllByAuthorAndStateCode(String author, String stateCode, Pageable pageable);

    Page<ArticleDTO> getAllByFeaturedAndAuthor(boolean featured, String author, Pageable pageable);

}
