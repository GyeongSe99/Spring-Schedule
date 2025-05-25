package com.sparta.schedule.comment.controller;

import com.sparta.schedule.comment.dto.CommentDto;
import com.sparta.schedule.comment.dto.CreateCommentRequestDto;
import com.sparta.schedule.comment.service.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    public ResponseEntity<CommentDto> createComment(CreateCommentRequestDto request, HttpSession session) {
        Long writerId = (Long) session.getAttribute("userId");
        if (writerId == null) {
            return ResponseEntity.status(401).build();
        }

        CommentDto response = commentService.createComment(writerId, request.getScheduleId(), request.getComment());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
