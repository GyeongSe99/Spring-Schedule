package com.sparta.schedule.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteUserRequestDto {

    private final String password;
}
