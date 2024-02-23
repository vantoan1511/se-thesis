package com.newswebsite.main.repository;

import com.newswebsite.main.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category findByAlias(String alias);

    List<Category> findAll();

    Page<Category> findAll(Pageable pageable);
}
