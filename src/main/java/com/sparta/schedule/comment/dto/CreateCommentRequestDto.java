package com.sparta.schedule.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCommentRequestDto {

    @NotBlank(message = "일정 아이디를 입력해주세요.")
    private final Long scheduleId;

    @NotBlank(message = "댓글 내용을 입력해주세요.")
    private final String comment;
}
