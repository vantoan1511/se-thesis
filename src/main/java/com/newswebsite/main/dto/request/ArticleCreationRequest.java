package com.newswebsite.main.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleCreationRequest {
    private Long id;
    private String title;
    private String alias;
    private String description;
    private String thumbnailUrl;
    private String text;
    private String categoryAlias;
}
