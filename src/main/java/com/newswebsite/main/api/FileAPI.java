package com.newswebsite.main.api;

import com.newswebsite.main.dto.request.FileRequest;
import com.newswebsite.main.dto.response.FileResponse;
import com.newswebsite.main.http.ErrorResponse;
import com.newswebsite.main.service.IFileWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/files")
public class FileAPI {

    @Autowired
    private IFileWriter fileWriter;

    @PostMapping
    public ResponseEntity<?> upload(@ModelAttribute FileRequest fileRequest) {
        try {
            FileResponse fileResponse = fileWriter.handleUpload(fileRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(fileWriter.save(fileResponse));
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ErrorResponse.builder()
                            .timestamp(new Date())
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message(ex.getMessage())
                            .build());
        }
    }
}
