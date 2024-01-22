package com.newswebsite.main.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO extends ContentDTO {
    private List<ArticleDTO> articles;
}
