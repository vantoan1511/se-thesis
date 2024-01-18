package com.newswebsite.main.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CategoryDTO {
    private long id;
    private String categoryCode;
    private String categoryName;
}
