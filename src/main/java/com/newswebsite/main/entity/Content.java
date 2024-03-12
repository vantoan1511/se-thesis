package com.newswebsite.main.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Content extends Audit {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "alias", unique = true, nullable = false)
    private String alias;

    @Column(name = "description")
    private String description;

    @Column(name = "published_at")
    private Date publishedAt;
}
