package com.newswebsite.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContentDTO {
    private Long id;
    private String alias;
    private String title;
    private String description;
    private Date createdAt;
    private Date lastModifiedAt;
    private String createdBy;
    private String lastModifiedBy;
    private Date publishedAt;

}
