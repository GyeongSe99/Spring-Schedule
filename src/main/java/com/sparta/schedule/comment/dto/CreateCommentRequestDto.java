package com.sparta.schedule.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCommentRequestDto {

    @NotNull(message = "일정 아이디는 필수 입니다.")
    private final Long scheduleId;

    @NotBlank(message = "댓글 내용을 입력해주세요.")
    private final String comment;
}
