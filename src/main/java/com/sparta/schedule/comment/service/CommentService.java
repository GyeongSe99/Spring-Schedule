package com.sparta.schedule.comment.service;

import com.sparta.schedule.comment.dto.CommentDto;
import com.sparta.schedule.comment.entity.Comment;
import com.sparta.schedule.comment.mapper.CommentMapper;
import com.sparta.schedule.comment.repository.CommentRepository;
import com.sparta.schedule.schedule.entity.Schedule;
import com.sparta.schedule.schedule.repository.ScheduleRepository;
import com.sparta.schedule.user.entity.User;
import com.sparta.schedule.user.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public List<CommentDto> getCommentsByScheduleId(Long scheduleId) {
        List<Comment> result = commentRepository.findByScheduleId(scheduleId);
        return result.stream()
                .map(CommentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto updateComment(Long id, String comment, Long userId) {
        Comment findComment = commentRepository.findByIdOrElseThrow(id);

        // 작성자 일치 여부 확인
        Long writerId = findComment.getSchedule().getUser().getId();
        if (!writerId.equals(userId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 사용자와 작성자가 일치하지 않습니다.");
        }

        // 작성자가 일치할 경우 수정 가능
        findComment.setContent(comment);
        Comment savedComment = commentRepository.save(findComment);

        return CommentMapper.toDto(savedComment);
    }

    public void deleteComment(Long id, Long userId) {
        Comment findComment = commentRepository.findByIdOrElseThrow(id);

        // 작성자 일치 여부 확인
        Long writerId = findComment.getSchedule().getUser().getId();
        if (!writerId.equals(userId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 사용자와 작성자가 일치하지 않습니다.");
        }

        commentRepository.deleteById(id);
    }
}
