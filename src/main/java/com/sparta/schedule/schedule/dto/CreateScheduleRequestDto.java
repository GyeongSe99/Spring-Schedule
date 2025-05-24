package com.sparta.schedule.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateScheduleRequestDto {

    @NotBlank(message = "제목을 입력해 주세요.")
    private final String title;

    private final String contents;

    @NotBlank(message = "작성자 아이디를 입력해주세요.")
    private final Long writerId;
}
