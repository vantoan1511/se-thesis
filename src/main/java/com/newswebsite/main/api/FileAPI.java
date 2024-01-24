package com.newswebsite.main.api;

import com.newswebsite.main.dto.ImageDTO;
import com.newswebsite.main.dto.request.ImageRequest;
import com.newswebsite.main.dto.response.ImageResponse;
import com.newswebsite.main.http.ErrorResponse;
import com.newswebsite.main.http.SuccessResponse;
import com.newswebsite.main.service.imageservice.IImageWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
public class FileAPI {

    @Autowired
    private IImageWriter imageWriter;

    @PostMapping
    public ResponseEntity<?> upload(@RequestParam CommonsMultipartFile file,
                                    @RequestParam String title) {
        try {
            ImageDTO imageDTO = imageWriter.handleUpload(file);
            imageDTO.setTitle(title);
            return ResponseEntity.status(HttpStatus.CREATED).body(imageWriter.save(imageDTO));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ErrorResponse.builder()
                            .timestamp(new Date())
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message(ex.getMessage())
                            .build());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ImageDTO imageDTO) {
        return ResponseEntity.ok(imageWriter.save(imageDTO));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody List<Long> ids) {
        try {
            imageWriter.deleteMultiple(ids);
            return ResponseEntity.ok(SuccessResponse.builder()
                    .timestamp(new Date())
                    .statusCode(HttpStatus.OK.value())
                    .message("Đã xóa thành công " + ids)
                    .build());
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ErrorResponse.builder()
                    .timestamp(new Date())
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .error(HttpStatus.BAD_REQUEST.name())
                    .message(ex.getMessage())
                    .build());
        }
    }
}
