package com.sparta.schedule.schedule.controller;

import com.sparta.schedule.schedule.dto.CreateScheduleRequestDto;
import com.sparta.schedule.schedule.dto.ScheduleDto;
import com.sparta.schedule.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleDto> createSchedule(@RequestBody CreateScheduleRequestDto request) {
        ScheduleDto response = scheduleService.createSchedule(
                request.getTitle(),
                request.getContents(),
                request.getWriterId()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
