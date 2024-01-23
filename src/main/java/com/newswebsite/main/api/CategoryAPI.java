package com.newswebsite.main.api;

import com.newswebsite.main.dto.CategoryDTO;
import com.newswebsite.main.http.SuccessResponse;
import com.newswebsite.main.service.categoryservice.ICategoryWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryAPI {

    @Autowired
    private ICategoryWriter categoryWriter;

    @PostMapping
    public CategoryDTO createNewCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        return categoryWriter.save(categoryDTO);
    }

    @PutMapping
    public CategoryDTO updateCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        return categoryWriter.save(categoryDTO);
    }

    @DeleteMapping
    public Object delete(@RequestBody List<Long> ids) {
        categoryWriter.deleteMultiple(ids);
        return SuccessResponse.builder()
                .timestamp(new Date())
                .statusCode(HttpStatus.NO_CONTENT.value())
                .message("Đã xóa thành công")
                .content(ids)
                .build();
    }
}
