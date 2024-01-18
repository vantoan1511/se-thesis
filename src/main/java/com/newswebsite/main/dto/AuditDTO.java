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
public class AuditDTO {
    private Long id;
    private Date createdAt;
    private Date lastModifiedAt;
    private String createdBy;
    private String lastModifiedBy;
}
