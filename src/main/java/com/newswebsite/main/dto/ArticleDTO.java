package com.newswebsite.main.dto;

import com.newswebsite.main.enums.ArticleState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleDTO extends AuditDTO {
    private String title;
    private String alias;
    private String thumbnailUrl;
    private String description;
    private String text;
    private boolean featured;
    private long traffic;
    private Date publishedAt;
    private String categoryCode;
    private String categoryName;
    private String stateCode = ArticleState.DRAFT.name();
    private String stateName;
}
