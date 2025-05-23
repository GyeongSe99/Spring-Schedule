package com.sparta.schedule.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteUserRequestDto {

    @NotBlank(message = "비밀번호는 필수입니다.")
    private final String password;
}
