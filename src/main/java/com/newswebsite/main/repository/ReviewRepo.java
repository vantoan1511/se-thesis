package com.newswebsite.main.repository;

import com.newswebsite.main.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo extends JpaRepository<Review, Long> {

    Page<Review> findAllByArticleId(long articleId, Pageable pageable);

    Page<Review> findAllByUserUsername(String username, Pageable pageable);
}
