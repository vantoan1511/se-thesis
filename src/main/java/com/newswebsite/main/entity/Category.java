package com.newswebsite.main.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "category_code", unique = true, nullable = false)
    private String code;
    @Column(name = "category_name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Article> articles;
}
