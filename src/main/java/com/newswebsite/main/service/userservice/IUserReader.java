package com.newswebsite.main.service.userservice;

import com.newswebsite.main.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserReader {

    boolean matches(String username, String password);

    boolean existsUsername(String username);

    boolean existsEmail(String email);

    boolean existsToken(String token);

    UserDTO findByToken(String token);

    UserDTO getUser(String username);

    Page<UserDTO> getAllUsers(Pageable pageable);
}
