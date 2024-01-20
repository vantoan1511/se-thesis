package com.newswebsite.main.api;

import com.newswebsite.main.dto.ArticleDTO;
import com.newswebsite.main.http.SuccessResponse;
import com.newswebsite.main.service.IArticleWriter;
import com.newswebsite.main.service.IArticleReader;
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
    private final IArticleReader articleReader;
    private final IArticleWriter articleWriter;

    @Autowired
    public ArticleAPI(IArticleReader articleReader, IArticleWriter articleWriter) {
        this.articleReader = articleReader;
        this.articleWriter = articleWriter;
    }

    @GetMapping
    public Object getArticles(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                              @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                              @RequestParam(value = "order", required = false, defaultValue = "ASC") String order,
                              @RequestParam(value = "by", required = false, defaultValue = "id") String by) {

        Sort.Direction direction = order.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = new PageRequest(page - 1, size, new Sort(direction, by));
        return articleReader.findAll(pageable);
    }

    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO articleDTO) {
        ArticleDTO savedArticle = articleWriter.save(articleDTO);
        URI articleURI = URI.create("/api/v1/articles/" + savedArticle.getId());
        return ResponseEntity.created(articleURI).body(savedArticle);
    }

    @PutMapping
    public ArticleDTO updateArticle(@RequestBody ArticleDTO articleDTO) {
        return articleWriter.save(articleDTO);
    }

    @PutMapping("/{id}/{action}")
    public Object handleAction(@PathVariable("id") long id,
                               @PathVariable("action") String action) {
        String message;
        switch (action) {
            case "submit" -> {
                articleWriter.submit(id);
                message = "Đã gửi yêu cầu đăng tải bài viết";
            }
            case "approve" -> {
                articleWriter.approve(id);
                message = "Yêu cầu đăng tải bải viết đã được chấp thuận";
            }
            case "reject" -> {
                articleWriter.reject(id);
                message = "Yêu cầu đăng tải bải viết đã bị từ chối";
            }
            case "publish" -> {
                articleWriter.publish(id);
                message = "Bài viết đã được đăng tải";
            }
            case "trash" -> {
                articleWriter.trash(id);
                message = "Bài viết đã chuyển vào thùng rác";
            }
            case "edit" -> {
                articleWriter.edit(id);
                message = "Bài viết đã chuyển sang chế độ chỉnh sửa";
            }
            case "unpublish" -> {
                articleWriter.unPublish(id);
                message = "Bài viết đã tạm ngừng đăng tải";
            }
            case "restore" -> {
                articleWriter.restore(id);
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
                articleWriter.approveMultiple(ids);
                message = "Yêu cầu đăng tải bải viết đã được chấp thuận";
            }
            case "reject" -> {
                articleWriter.rejectMultiple(ids);
                message = "Yêu cầu đăng tải bải viết đã bị từ chối";
            }
            case "trash" -> {
                articleWriter.trashMultiple(ids);
                message = "Bài viết đã chuyển vào thùng rác";
            }
            case "restore" -> {
                articleWriter.restoreMultiple(ids);
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
        articleWriter.deleteArticles(ids);
        return ResponseEntity.ok(SuccessResponse.builder()
                .timestamp(new Date())
                .statusCode(HttpStatus.OK.value())
                .message("Đã xóa thành công ")
                .content(ids)
                .build());
    }
}
