package com.sparta.schedule.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class ScheduleDto {

    private final Long id;

    private final String title;

    private final String contents;

    private final Long writerId;

    private final LocalDate createdAt;

    private final LocalDate updatedAt;
}
