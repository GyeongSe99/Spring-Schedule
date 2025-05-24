package com.sparta.schedule.schedule.mapper;

import com.sparta.schedule.schedule.dto.ScheduleDto;
import com.sparta.schedule.schedule.entity.Schedule;

public class ScheduleMapper {
    public static ScheduleDto toDto(Schedule schedule) {
        return ScheduleDto.builder()
                .id(schedule.getId())
                .title(schedule.getTitle())
                .contents(schedule.getContents())
                .writerId(schedule.getUser().getId())
                .createdAt(schedule.getCreatedAt())
                .updatedAt(schedule.getUpdatedAt())
                .build();
    }
}
