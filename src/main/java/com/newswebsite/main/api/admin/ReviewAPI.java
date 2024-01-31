package com.newswebsite.main.api.admin;

import com.newswebsite.main.dto.ReviewDTO;
import com.newswebsite.main.service.reviewservice.IReviewWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewAPI {

    private final IReviewWriter reviewWriter;

    @Autowired
    public ReviewAPI(IReviewWriter reviewWriter) {
        this.reviewWriter = reviewWriter;
    }

    @PostMapping
    public ReviewDTO create(@RequestBody @Valid ReviewDTO reviewDTO) {
        return reviewWriter.save(reviewDTO);
    }
}
