package com.newswebsite.main.service.reviewservice;

import com.newswebsite.main.dto.ReviewDTO;
import com.newswebsite.main.mapper.CollectionMapper;
import com.newswebsite.main.repository.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReviewReader implements IReviewReader {

    private final ReviewRepo reviewRepo;

    private final CollectionMapper mapper;

    @Autowired
    public ReviewReader(ReviewRepo reviewRepo, CollectionMapper mapper) {
        this.reviewRepo = reviewRepo;
        this.mapper = mapper;
    }

    @Override
    public Page<ReviewDTO> getArticleReviews(long articleId, Pageable pageable) {
        return reviewRepo.findAllByArticleId(articleId, pageable)
                .map(item -> mapper.map(item, ReviewDTO.class));
    }

    @Override
    public Page<ReviewDTO> getAllReviewsByUsername(String username, Pageable pageable) {
        return reviewRepo.findAllByUserUsername(username, pageable)
                .map(item -> mapper.map(item, ReviewDTO.class));
    }
}
