package com.newswebsite.main.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "article")
@Getter
@Setter
public class Article extends Audit {

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
    @Column(name = "published_at")
    private Date publishedAt;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;
}
