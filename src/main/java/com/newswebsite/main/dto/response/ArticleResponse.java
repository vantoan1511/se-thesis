package com.newswebsite.main.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse extends AuditResponse {
    private long id;
    private String title;
    private String alias;
    private String thumbnailUrl;
    private String description;
    private String text;
    private Date publishedAt;
    private long traffic;
    private boolean featured;
}
