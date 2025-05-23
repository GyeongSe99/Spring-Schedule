package com.sparta.schedule.controller;

import com.sparta.schedule.dto.*;
import com.sparta.schedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable("id") Long id,
                                               @RequestBody UpdatePasswordRequestDto request) {
        userService.updatePassword(id, request.getOldPassword(), request.getNewPassword());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id,
                                           @RequestBody DeleteUserRequestDto request) {
        userService.deleteUser(id, request.getPassword());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
