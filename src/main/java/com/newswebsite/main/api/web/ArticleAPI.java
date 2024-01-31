package com.newswebsite.main.api.web;

import com.newswebsite.main.dto.ReviewDTO;
import com.newswebsite.main.service.reviewservice.IReviewReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController(value = "WebArticleAPI")
@RequestMapping("/public/api/v1/articles")
public class ArticleAPI {

    private final IReviewReader reviewReader;

    @Autowired
    public ArticleAPI(IReviewReader reviewReader) {
        this.reviewReader = reviewReader;
    }

    @GetMapping("/{id}/reviews")
    public Page<ReviewDTO> getArticleReviews(@PathVariable("id") Long id,
                                             @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                             @RequestParam(value = "size", required = false, defaultValue = "2") int size) {
        return reviewReader.getArticleReviews(id, new PageRequest(page - 1, size, new Sort(Sort.Direction.DESC, "createdAt")));
    }
}
