package com.sparta.schedule.user.mapper;

import com.sparta.schedule.user.dto.UserResponseDto;
import com.sparta.schedule.user.entity.User;

public class UserMapper {
    public static UserResponseDto toDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
