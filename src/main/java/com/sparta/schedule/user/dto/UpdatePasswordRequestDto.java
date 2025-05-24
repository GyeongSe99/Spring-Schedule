package com.sparta.schedule.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePasswordRequestDto {

    private final String oldPassword;

    private final String newPassword;
}
