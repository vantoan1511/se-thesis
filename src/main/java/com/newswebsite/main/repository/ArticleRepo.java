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
            "AND (:startDate IS NULL OR a.publishedAt >= :startDate) " +
            "AND a.state.id=5 ORDER BY a.publishedAt DESC")
    Page<Article> search(@Param("q") String q,
                         @Param("categoryIds") List<Long> categoryIds,
                         @Param("startDate") Date startDate, Pageable pageable);

    Page<Article> findAll(Pageable pageable);

    @Query(value = "SELECT DISTINCT a FROM Article a LEFT JOIN FETCH a.category c WHERE a.featured=:featured AND a.state.code=:stateCode ORDER BY a.publishedAt DESC",
            countQuery = "SELECT COUNT( DISTINCT a) FROM Article a LEFT JOIN a.category c WHERE a.featured=:featured AND a.state.code=:stateCode")
    Page<Article> findAllPublishedAndFeaturedArticles(@Param("featured") boolean featured, @Param("stateCode") String stateCode, Pageable pageable);

    @Query(value = "SELECT DISTINCT a FROM Article a LEFT JOIN FETCH a.category WHERE a.state.code=:stateCode ORDER BY a.publishedAt DESC ",
            countQuery = "SELECT COUNT(DISTINCT a) FROM Article a LEFT JOIN a.category WHERE a.state.code=:stateCode")
    Page<Article> findAllLatestArticles(@Param("stateCode") String stateCode, Pageable pageable);

    Page<Article> findAllByStateCode(String stateCode, Pageable pageable);

    Page<Article> findAllByStateCodeNot(String stateCode, Pageable pageable);

    @Query("SELECT a FROM Article a INNER JOIN a.category c LEFT JOIN c.parent p " +
            "WHERE (c.alias=:categoryAlias OR p.alias=:categoryAlias) AND a.state.code='PUBLISHED'")
    Page<Article> searchAllPublishedByCategoryAlias(@Param("categoryAlias") String categoryAlias, Pageable pageable);

    Page<Article> findAllByCreatedByAndStateCode(String createdBy, String stateCode, Pageable pageable);

    Page<Article> findAllByCreatedByAndStateCodeNot(String createdBy, String stateCode, Pageable pageable);

    Page<Article> findAllByFeaturedAndCreatedByAndStateCodeNot(boolean featured, String createdBy, String stateCode, Pageable pageable);

}
