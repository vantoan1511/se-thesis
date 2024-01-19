package com.newswebsite.main.api;

import com.newswebsite.main.dto.ArticleDTO;
import com.newswebsite.main.http.ErrorResponse;
import com.newswebsite.main.http.SuccessResponse;
import com.newswebsite.main.service.IArticleModificationService;
import com.newswebsite.main.service.IArticleRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleAPI {
    private final IArticleRetrievalService articleRetrievalService;
    private final IArticleModificationService articleModificationService;

    @Autowired
    public ArticleAPI(IArticleRetrievalService articleRetrievalService, IArticleModificationService articleModificationService) {
        this.articleRetrievalService = articleRetrievalService;
        this.articleModificationService = articleModificationService;
    }

    @GetMapping
    public Object getArticles(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                              @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                              @RequestParam(value = "order", required = false, defaultValue = "ASC") String order,
                              @RequestParam(value = "by", required = false, defaultValue = "id") String by) {

        Sort.Direction direction = order.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = new PageRequest(page - 1, size, new Sort(direction, by));
        return articleRetrievalService.findAll(pageable);
    }

    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO articleDTO) {
        ArticleDTO savedArticle = articleModificationService.save(articleDTO);
        URI articleURI = URI.create("/api/v1/articles/" + savedArticle.getId());
        return ResponseEntity.created(articleURI).body(savedArticle);
    }

    @PutMapping
    public ArticleDTO updateArticle(@RequestBody ArticleDTO articleDTO) {
        return articleModificationService.save(articleDTO);
    }

    @PutMapping("/{id}/{action}")
    public Object handleAction(@PathVariable("id") long id,
                               @PathVariable("action") String action) {
        try {
            switch (action) {
                case "submit" -> articleModificationService.submitArticle(id);
                default -> throw new IllegalArgumentException("Invalid action: " + action);
            }
            return ResponseEntity.ok(SuccessResponse.builder()
                    .timestamp(new Date())
                    .statusCode(HttpStatus.OK.value())
                    .message("Đã gửi yêu cầu duyệt bài")
                    .content(id)
                    .build());
        } catch (RuntimeException ex) {
            return ErrorResponse.builder()
                    .timestamp(new Date())
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .error(HttpStatus.NOT_FOUND.name())
                    .message(ex.getMessage())
                    .build();
        }
    }

    @DeleteMapping
    public Object deleteArticles(@RequestBody List<Long> ids) {
        try {
            articleModificationService.deleteArticles(ids);
            return ResponseEntity.ok(SuccessResponse.builder()
                    .timestamp(new Date())
                    .statusCode(HttpStatus.OK.value())
                    .message("Deleted successfully!")
                    .content(ids)
                    .build());
        } catch (Exception ex) {
            return ErrorResponse.builder()
                    .timestamp(new Date())
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .error(HttpStatus.NOT_FOUND.name())
                    .message(ex.getMessage())
                    .build();
        }
    }

}
