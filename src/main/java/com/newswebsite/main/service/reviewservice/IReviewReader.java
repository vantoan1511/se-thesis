package com.newswebsite.main.service.reviewservice;

import com.newswebsite.main.dto.ReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IReviewReader {
    Page<ReviewDTO> getArticleReviews(long articleId, Pageable pageable);
}
