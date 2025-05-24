package com.sparta.schedule.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class SignUpResponseDto {

    private final Long id;
    private final String username;
    private final String email;
    private final LocalDate createdAt;
    private final LocalDate updatedAt;
}
