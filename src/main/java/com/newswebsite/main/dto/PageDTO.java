package com.newswebsite.main.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PageDTO {
    private int page;
    private int limit;
    private int totalPages;
    private int totalItems;
    private int numberOfItems;
    private boolean first;
    private boolean last;
    private String sortBy;
    private String sortOrder;
    private Object contents;
}
