package com.newswebsite.main.api.admin;

import com.newswebsite.main.dto.CategoryDTO;
import com.newswebsite.main.dto.request.CategoryCreationRequest;
import com.newswebsite.main.http.SuccessResponse;
import com.newswebsite.main.service.categoryservice.ICategoryWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryAPI {

    private final ICategoryWriter categoryWriter;

    @Autowired
    public CategoryAPI(ICategoryWriter categoryWriter) {
        this.categoryWriter = categoryWriter;
    }

    @PostMapping
    public CategoryDTO createNewCategory(@RequestBody CategoryCreationRequest categoryCreationRequest) {
        return categoryWriter.save(categoryCreationRequest);
    }

    @PutMapping
    public CategoryDTO updateCategory(@RequestBody CategoryCreationRequest categoryCreationRequest) {
        return categoryWriter.save(categoryCreationRequest);
    }

    @DeleteMapping
    public Object delete(@RequestBody List<Long> ids) {
        categoryWriter.deleteMultiple(ids);
        return ResponseEntity.ok(new SuccessResponse(new Date(), HttpStatus.OK, "Đã xóa thành công", ids));
    }
}
