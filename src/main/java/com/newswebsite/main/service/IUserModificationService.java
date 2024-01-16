package com.newswebsite.main.service;

import com.newswebsite.main.dto.UserDTO;

public interface IUserModificationService {
    void register(UserDTO newUserDTO);

    void resetPassword(String email);
}
