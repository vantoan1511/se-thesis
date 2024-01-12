package com.newswebsite.main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
    private Long id;
    private String title;
    private String alias;
    private String thumbnailUrl;
    private String description;
    private String text;
    private boolean featured;
    private long traffic;
    private Date createdAt;
    private Date lastModifiedAt;
    private Date publishedAt;
}
