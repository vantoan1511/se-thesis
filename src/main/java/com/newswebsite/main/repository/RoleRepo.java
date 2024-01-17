package com.newswebsite.main.repository;

import com.newswebsite.main.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepo extends JpaRepository<Role, Long> {

    List<Role> findAll();

    Role findById(long id);

    Role findByAuthority(String authority);
}
