package com.newswebsite.main.repository;

import com.newswebsite.main.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category findByCategoryCode(String categoryCode);

    List<Category> findAll();
}
