package com.newswebsite.main.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "article")
@Getter
@Setter
public class Article extends Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "thumbnail_url", columnDefinition = "TEXT")
    private String thumbnailUrl;
    @Column(name = "text", columnDefinition = "TEXT")
    private String text;
    @Column(columnDefinition = "tinyint default 0")
    private boolean featured;
    @Column(columnDefinition = "bigint default 0")
    private long traffic;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;
}
