package com.sparta.schedule.schedule.service;

import com.sparta.schedule.schedule.dto.ScheduleDto;
import com.sparta.schedule.schedule.entity.Schedule;
import com.sparta.schedule.schedule.mapper.ScheduleMapper;
import com.sparta.schedule.schedule.repository.ScheduleRepository;
import com.sparta.schedule.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleDto createSchedule(String title, String contents, Long writerId) {
        Schedule newSchedule = new Schedule(title, contents);
        newSchedule.setUser(userRepository.findByIdOrElseThrow(writerId));
        Schedule saved = scheduleRepository.save(newSchedule);

        return ScheduleMapper.toDto(saved);
    }

    public ScheduleDto getSchedule(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        return ScheduleMapper.toDto(findSchedule);
    }
}
