package com.newswebsite.main.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "state")
@Getter
@Setter
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "state_code", unique = true, nullable = false)
    private String code;
    @Column(name = "state_name")
    private String name;

    @OneToMany(mappedBy = "state")
    private List<Article> articles;
}
