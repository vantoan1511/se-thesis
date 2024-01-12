package com.newswebsite.main.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "article")
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "alias")
    private String alias;
    @Column(name = "thumbnail_url", columnDefinition = "TEXT")
    private String thumbnailUrl;
    @Column(name = "description")
    private String description;
    @Column(name = "text", columnDefinition = "TEXT")
    private String text;
    @Column(columnDefinition = "tinyint default 0")
    private boolean featured;
    @Column(columnDefinition = "bigint default 0")
    private long traffic;
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;
    @LastModifiedDate
    @Column(name = "last_modified_at")
    private Date lastModifiedAt;
    @Column(name = "published_at")
    private Date publishedAt;
}
