package com.newswebsite.main.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}
