package com.newswebsite.main.repository;

import com.newswebsite.main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query("SELECT u FROM User u JOIN FETCH u.authorities a WHERE u.username=:username")
    User findByUsername(@Param("username") String username);

    User findByToken(String token);
}
