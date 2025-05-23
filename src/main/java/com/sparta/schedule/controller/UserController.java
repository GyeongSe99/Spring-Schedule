package com.sparta.schedule.controller;

import com.sparta.schedule.dto.SignUpRequestDto;
import com.sparta.schedule.dto.SignUpResponseDto;
import com.sparta.schedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto request) {

        SignUpResponseDto response = userService.signUp(
                request.getUsername(),
                request.getPassword(),
                request.getEmail()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
