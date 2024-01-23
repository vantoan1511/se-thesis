package com.newswebsite.main.service.userservice;

import com.newswebsite.main.dto.UserDTO;

public interface IUserModificationService {
    void register(UserDTO newUserDTO);

    void resetPassword(String email);

    void changePassword(String token, String newPassword);
}
