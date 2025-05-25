package com.sparta.schedule.comment.mapper;

import com.sparta.schedule.comment.dto.CommentDto;
import com.sparta.schedule.comment.entity.Comment;

public class CommentMapper {
    public static CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .writerId(comment.getUser().getId())
                .scheduleId(comment.getSchedule().getId())
                .comment(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }
}
