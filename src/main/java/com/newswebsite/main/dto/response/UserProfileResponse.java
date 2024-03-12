package com.newswebsite.main.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileResponse {
    private Long id;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private Date createdAt;
    private List<String> authorities;
    private boolean enabled;

    public String getFullName() {
        return firstName.concat(" ").concat(lastName);
    }
}
