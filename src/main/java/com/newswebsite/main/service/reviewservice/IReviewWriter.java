package com.newswebsite.main.service.reviewservice;

import com.newswebsite.main.dto.ReviewDTO;

import java.util.List;

public interface IReviewWriter {

    ReviewDTO save(ReviewDTO reviewDTO);

    ReviewDTO update(ReviewDTO reviewDTO);

    void delete(long id);

    void deleteMultiple(List<Long> ids);
}
