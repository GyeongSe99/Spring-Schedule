package com.sparta.schedule.schedule.controller;

import com.sparta.schedule.schedule.dto.CreateScheduleRequestDto;
import com.sparta.schedule.schedule.dto.ScheduleDto;
import com.sparta.schedule.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDto> getSchedule(@PathVariable Long id) {
        ScheduleDto response = scheduleService.getSchedule(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleDto>> getSchedulesByUpdatedAtAndWriterId(@RequestParam(value = "updatedAt", required = false)LocalDate updatedAt,
                                                          @RequestParam(value = "writerId", required = false) Long writerId) {
        List<ScheduleDto> response = scheduleService.getSchedulesByUpdatedAtAndWriterId(updatedAt, writerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
