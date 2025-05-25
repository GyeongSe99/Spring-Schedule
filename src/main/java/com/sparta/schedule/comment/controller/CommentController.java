package com.sparta.schedule.comment.controller;

import com.sparta.schedule.comment.dto.CommentDto;
import com.sparta.schedule.comment.dto.CreateCommentRequestDto;
import com.sparta.schedule.comment.dto.UpdateCommentRequestDto;
import com.sparta.schedule.comment.service.CommentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody @Valid CreateCommentRequestDto request, HttpSession session) {
        Long writerId = (Long) session.getAttribute("userId");
        if (writerId == null) {
            return ResponseEntity.status(401).build();
        }

        CommentDto response = commentService.createComment(writerId, request.getScheduleId(), request.getComment());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable Long id) {
        CommentDto response = commentService.getComment(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getCommentsByScheduleId(@RequestParam("scheduleId") Long scheduleId) {
        List<CommentDto> response = commentService.getCommentsByScheduleId(scheduleId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long id, @RequestBody @Valid UpdateCommentRequestDto request, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        CommentDto response = commentService.updateComment(id, request.getComment(), userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        commentService.deleteComment(id, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
