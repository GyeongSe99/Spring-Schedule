package com.sparta.schedule.schedule.service;

import com.sparta.schedule.schedule.dto.ScheduleDto;
import com.sparta.schedule.schedule.entity.Schedule;
import com.sparta.schedule.schedule.mapper.ScheduleMapper;
import com.sparta.schedule.schedule.repository.ScheduleRepository;
import com.sparta.schedule.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<ScheduleDto> getSchedulesByUpdatedAtAndWriterId(LocalDate updatedAt, Long writerId) {

        List<Schedule> result = scheduleRepository.findSchedulesByUpdatedAtAndWriterId(updatedAt, writerId);

        return result.stream()
                .map(ScheduleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ScheduleDto updateSchedule(Long id, String title, String contents) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        if (title != null) {
            findSchedule.setTitle(title);
        }
        if (contents != null) {
            findSchedule.setContents(contents);
        }

        Schedule savedSchedule = scheduleRepository.save(findSchedule);
        return ScheduleMapper.toDto(savedSchedule);
    }
}
