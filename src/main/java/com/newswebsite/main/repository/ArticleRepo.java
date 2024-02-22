package com.newswebsite.main.repository;

import com.newswebsite.main.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ArticleRepo extends JpaRepository<Article, Long> {

    Article findByAlias(String alias);

    Article findByAliasAndStateCode(String alias, String stateCode);

    @Query("SELECT a FROM Article a " +
            "WHERE CONCAT(a.title, ' ', a.description, ' ', a.text) LIKE %:q% " +
            "AND (a.category.id IN (:categoryIds))" +
            "AND :startDate IS NULL OR a.publishedAt >= :startDate " +
            "AND a.state.id=5 ORDER BY a.publishedAt DESC")
    Page<Article> search(@Param("q") String q,
                         @Param("categoryIds") List<Long> categoryIds,
                         @Param("startDate") Date startDate, Pageable pageable);

    Page<Article> findAll(Pageable pageable);

    Page<Article> findAllByFeatured(boolean featured, Pageable pageable);

    Page<Article> findAllByStateCode(String stateCode, Pageable pageable);

    Page<Article> findAllByStateCodeNot(String stateCode, Pageable pageable);

    Page<Article> findAllByCreatedByAndStateCode(String createdBy, String stateCode, Pageable pageable);

    Page<Article> findAllByCreatedByAndStateCodeNot(String createdBy, String stateCode, Pageable pageable);

    Page<Article> findAllByFeaturedAndCreatedByAndStateCodeNot(boolean featured, String createdBy, String stateCode, Pageable pageable);

}
