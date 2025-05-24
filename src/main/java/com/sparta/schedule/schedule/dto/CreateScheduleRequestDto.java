package com.sparta.schedule.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateScheduleRequestDto {

    private final String title;

    private final String contents;

    private final Long writerId;
}
