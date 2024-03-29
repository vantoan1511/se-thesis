package com.newswebsite.main.repository;

import com.newswebsite.main.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepo extends JpaRepository<State, Long> {
    State findByCode(String code);

    List<State> findAll();
}
