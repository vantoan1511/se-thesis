package com.newswebsite.main.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "state")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "state_id")
    private long id;

    @Column(name = "state_code", unique = true, nullable = false)
    private String code;

    @Column(name = "state_name")
    private String name;

    @OneToMany(mappedBy = "state")
    private List<Article> articles;
}
