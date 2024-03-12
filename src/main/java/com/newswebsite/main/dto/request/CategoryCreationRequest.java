package com.newswebsite.main.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryCreationRequest {
    private Long id;
    private String title;
    private String alias;
    private String description;
    private String parentAlias;
}
