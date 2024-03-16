package com.newswebsite.main.repository;

import com.newswebsite.main.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    @Query("SELECT c, p FROM Category c LEFT JOIN c.parent p WHERE c.alias = :alias")
    Category findByAlias(@Param("alias") String alias);

    List<Category> findAll();

    Page<Category> findAll(Pageable pageable);
}
