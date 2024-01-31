package com.newswebsite.main.service.reviewservice;

import com.newswebsite.main.dto.ReviewDTO;

public interface IReviewWriter {

    ReviewDTO save(ReviewDTO reviewDTO);
}
