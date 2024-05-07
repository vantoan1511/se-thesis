package com.newswebsite.main.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleWithCategoryAndState extends ArticleResponse {
    private String categoryTitle;
    private String categoryAlias;
    private String stateCode;
    private String stateName;
}
