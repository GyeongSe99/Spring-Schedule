package com.sparta.schedule.schedule.repository;

import com.sparta.schedule.schedule.entity.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepositoryCustom {

    List<Schedule> findSchedulesByUpdatedAtAndWriterId(LocalDate updatedAt, Long writerId);
}
