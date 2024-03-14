package com.newswebsite.main.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PopularArticleResponse {
    private String title;
    private String thumbnailUrl;
    private String alias;
}
