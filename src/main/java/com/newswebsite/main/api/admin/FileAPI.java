package com.newswebsite.main.api.admin;

import com.newswebsite.main.dto.ImageDTO;
import com.newswebsite.main.http.ErrorResponse;
import com.newswebsite.main.http.SuccessResponse;
import com.newswebsite.main.service.imageservice.IImageWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
            return new ResponseEntity<>(new ErrorResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
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
            return ResponseEntity.ok(new SuccessResponse(new Date(), HttpStatus.OK, "Đã xóa thành công", ids));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(new ErrorResponse(new Date(), HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex.getMessage()));
        }
    }
}
