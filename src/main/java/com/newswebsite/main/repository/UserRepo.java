package com.newswebsite.main.repository;

import com.newswebsite.main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByUsername(String username);
}
