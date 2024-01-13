package com.newswebsite.main.service;

import com.newswebsite.main.dto.UserDTO;

public interface IUserRetrievalService {
    boolean existsUsername(String username);

    boolean existsEmail(String email);
}
