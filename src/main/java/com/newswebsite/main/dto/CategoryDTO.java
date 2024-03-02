package com.newswebsite.main.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO extends ContentDTO {
    private String parentAlias;
    private String parentTitle;
    private List<CategoryDTO> subCategories;
    private List<ArticleDTO> articles;
}
