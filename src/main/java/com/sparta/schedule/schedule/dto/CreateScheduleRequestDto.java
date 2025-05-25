package com.sparta.schedule.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateScheduleRequestDto {

    @NotBlank(message = "제목을 입력해 주세요.")
    private final String title;

    private final String contents;

    @NotNull(message = "작성자 ID는 필수입니다.")
    private final Long writerId;
}
