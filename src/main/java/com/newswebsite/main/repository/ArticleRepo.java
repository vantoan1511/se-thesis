package com.newswebsite.main.repository;

import com.newswebsite.main.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepo extends JpaRepository<Article, Long> {

    Article findByAlias(String alias);

    Page<Article> findAll(Pageable pageable);

    Page<Article> findAllByCreatedByAndStateCode(String createdBy, String stateCode, Pageable pageable);

    Page<Article> findAllByCreatedByAndStateCodeNot(String createdBy, String stateCode, Pageable pageable);

    Page<Article> findAllByFeaturedAndCreatedByAndStateCodeNot(boolean featured, String createdBy, String stateCode, Pageable pageable);

}
