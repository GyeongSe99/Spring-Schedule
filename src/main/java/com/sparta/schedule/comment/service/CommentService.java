package com.sparta.schedule.comment.service;

import com.sparta.schedule.comment.dto.CommentDto;
import com.sparta.schedule.comment.entity.Comment;
import com.sparta.schedule.comment.mapper.CommentMapper;
import com.sparta.schedule.comment.repository.CommentRepository;
import com.sparta.schedule.schedule.entity.Schedule;
import com.sparta.schedule.schedule.repository.ScheduleRepository;
import com.sparta.schedule.user.entity.User;
import com.sparta.schedule.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentDto createComment(Long writerId, Long scheduleId, String comment) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(scheduleId);
        User user = userRepository.findByIdOrElseThrow(writerId);

        Comment newComment = new Comment();
        newComment.setContent(comment);
        newComment.setUser(user);
        newComment.setSchedule(schedule);

        Comment savedComment = commentRepository.save(newComment);

        return CommentMapper.toDto(savedComment);
    }


    public CommentDto getComment(Long id) {
        Comment findComment = commentRepository.findByIdOrElseThrow(id);
        return CommentMapper.toDto(findComment);
    }
}
