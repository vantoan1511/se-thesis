package com.newswebsite.main.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequest extends ContentRequest {
    private String thumbnailUrl;
    private String text;
    private boolean featured;
    @NotEmpty(message = "Chuyên mục là bắt buộc")
    private String categoryAlias;
    private String stateCode;
}
