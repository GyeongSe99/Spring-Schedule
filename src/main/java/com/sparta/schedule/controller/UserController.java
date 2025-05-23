package com.sparta.schedule.controller;

import com.sparta.schedule.dto.SignUpRequestDto;
import com.sparta.schedule.dto.SignUpResponseDto;
import com.sparta.schedule.dto.UserResponseDto;
import com.sparta.schedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        List<UserResponseDto> response = userService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
