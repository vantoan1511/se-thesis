package com.newswebsite.main.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LatestArticleResponse {
    private String title;
    private String thumbnailUrl;
    private String alias;
    private String description;
    private String categoryTitle;
    private String categoryAlias;
    private Date publishedAt;
    private Long traffic;
}
