package com.newswebsite.main.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleResponse extends ContentResponse {
    private String thumbnailUrl;
    private String text;
    private boolean featured;
    private long traffic;
    private String categoryAlias;
    private String categoryTitle;
    private String stateCode;
    private String stateName;
}
