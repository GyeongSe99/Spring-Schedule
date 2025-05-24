package com.sparta.schedule.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpRequestDto {

    @NotBlank(message = "이름을 입력해주세요.")
    private final String username;

    // 비밀번호 : 영문자 하나 이상 포함, 숫자 하나 이상 포함, 영문자 또는 숫자로 4~30자 구성
    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,30}$",
            message = "비밀번호는 영문자와 숫자를 포함한 4자 이상 30자 이하여야 합니다.")
    @Size(min = 4, max = 30, message = "비밀번호는 4자 이상 30자 이하로 입력해주세요.")
    private final String password;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "올바른 이메일 형식을 입력해주세요.")
    private final String email;

}

