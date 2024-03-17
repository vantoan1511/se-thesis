package com.newswebsite.main.dto;

import com.newswebsite.main.enums.ArticleState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleDTO extends ContentDTO {
    private Long articleId;
    private String thumbnailUrl;
    private String text;
    private boolean featured;
    private long traffic;
    @NotEmpty(message = "Chuyên mục là bắt buộc")
    private String categoryAlias;
    private String categoryTitle;
    private String stateCode = ArticleState.DRAFT.name();
    private String stateName = ArticleState.DRAFT.getValue();
}
