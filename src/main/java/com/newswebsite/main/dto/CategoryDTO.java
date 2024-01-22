package com.newswebsite.main.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO extends AuditDTO {
    private String code;
    private String name;
    private List<ArticleDTO> articles;
}
