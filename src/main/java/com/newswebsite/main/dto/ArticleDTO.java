package com.newswebsite.main.dto;

import com.newswebsite.main.enums.ArticleState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleDTO extends ContentDTO {
    private String thumbnailUrl;
    private String text;
    private boolean featured;
    private long traffic;
    private String categoryAlias;
    private String categoryTitle;
    private String stateCode = ArticleState.DRAFT.name();
    private String stateName;
}
