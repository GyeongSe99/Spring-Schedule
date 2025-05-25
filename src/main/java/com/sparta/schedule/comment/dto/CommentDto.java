package com.sparta.schedule.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class CommentDto {

    private final Long writerId;

    private final Long scheduleId;

    private final String comment;

    private final LocalDate createdAt;

    private final LocalDate updatedAt;
}
