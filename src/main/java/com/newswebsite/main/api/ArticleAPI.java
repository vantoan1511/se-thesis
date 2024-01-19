package com.newswebsite.main.api;

import com.newswebsite.main.dto.ArticleDTO;
import com.newswebsite.main.enums.ArticleState;
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
        String message;
        switch (action) {
            case "submit" -> {
                articleModificationService.submit(id);
                message = "Đã gửi yêu cầu đăng tải bài viết";
            }
            case "approve" -> {
                articleModificationService.approve(id);
                message = "Yêu cầu đăng tải bải viết đã được chấp thuận";
            }
            case "reject" -> {
                articleModificationService.reject(id);
                message = "Yêu cầu đăng tải bải viết đã bị từ chối";
            }
            case "publish" -> {
                articleModificationService.publish(id);
                message = "Bài viết đã được đăng tải";
            }
            case "trash" -> {
                articleModificationService.trash(id);
                message = "Bài viết đã chuyển vào thùng rác";
            }
            case "edit" -> {
                articleModificationService.edit(id);
                message = "Bài viết đã chuyển sang chế độ chỉnh sửa";
            }
            case "unpublish" -> {
                articleModificationService.unPublish(id);
                message = "Bài viết đã tạm ngừng đăng tải";
            }
            case "restore" -> {
                articleModificationService.restore(id);
                message = "Bài viết đã được khôi phục";
            }
            default -> throw new IllegalArgumentException("Thao tác không hợp lệ: " + action);
        }
        return ResponseEntity.ok(SuccessResponse.builder()
                .timestamp(new Date())
                .statusCode(HttpStatus.OK.value())
                .message(message)
                .content(id)
                .build());
    }

    @PutMapping("/{action}")
    public Object handleActions(@PathVariable("action") String action,
                                @RequestBody List<Long> ids) {
        String message;
        switch (action) {
            case "approve" -> {
                articleModificationService.approveMultiple(ids);
                message = "Yêu cầu đăng tải bải viết đã được chấp thuận";
            }
            case "reject" -> {
                articleModificationService.rejectMultiple(ids);
                message = "Yêu cầu đăng tải bải viết đã bị từ chối";
            }
            case "trash" -> {
                articleModificationService.trashMultiple(ids);
                message = "Bài viết đã chuyển vào thùng rác";
            }
            case "restore" -> {
                articleModificationService.restoreMultiple(ids);
                message = "Bài viết đã được khôi phục";
            }
            default -> throw new IllegalArgumentException("Thao tác không hợp lệ: " + action);
        }
        return ResponseEntity.ok(SuccessResponse.builder()
                .timestamp(new Date())
                .statusCode(HttpStatus.OK.value())
                .message(message)
                .content(ids)
                .build());
    }

    @DeleteMapping
    public Object deleteArticles(@RequestBody List<Long> ids) {
        articleModificationService.deleteArticles(ids);
        return ResponseEntity.ok(SuccessResponse.builder()
                .timestamp(new Date())
                .statusCode(HttpStatus.OK.value())
                .message("Đã xóa thành công ")
                .content(ids)
                .build());
    }
}
