package com.newswebsite.main.mapper;

import com.newswebsite.main.dto.request.UserRegistrationRequest;
import com.newswebsite.main.dto.response.UserProfileResponse;
import com.newswebsite.main.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "authorities", expression = "java(mapAuthorities(user.getAuthorities()))")
    UserProfileResponse toUserProfileResponse(User user);

    User toUser(UserRegistrationRequest request);

    default List<String> mapAuthorities(Collection<? extends GrantedAuthority> roles) {
        return roles.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
}
