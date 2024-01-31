package com.newswebsite.main.api.web;

import com.newswebsite.main.dto.ReviewDTO;
import com.newswebsite.main.http.SuccessResponse;
import com.newswebsite.main.service.reviewservice.IReviewWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

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

    @PutMapping
    public ReviewDTO update(@RequestBody @Valid ReviewDTO reviewDTO) {
        return reviewWriter.update(reviewDTO);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody List<Long> ids) {
        reviewWriter.deleteMultiple(ids);
        return ResponseEntity.ok(new SuccessResponse(new Date(), HttpStatus.OK, "Đã xóa thành công", ids));
    }
}
