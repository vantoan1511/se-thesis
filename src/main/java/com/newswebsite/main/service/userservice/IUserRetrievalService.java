package com.newswebsite.main.service.userservice;

import com.newswebsite.main.dto.UserDTO;

public interface IUserRetrievalService {
    boolean existsUsername(String username);

    boolean existsEmail(String email);

    boolean existsToken(String token);

    UserDTO findByToken(String token);
}
