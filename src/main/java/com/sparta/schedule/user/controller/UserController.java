package com.sparta.schedule.user.controller;

import com.sparta.schedule.user.service.UserService;
import com.sparta.schedule.user.dto.*;
import jakarta.validation.Valid;
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


    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        List<UserResponseDto> response = userService.getUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable("id") Long id,
                                               @RequestBody @Valid UpdatePasswordRequestDto request) {
        userService.updatePassword(id, request.getOldPassword(), request.getNewPassword());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id,
                                           @RequestBody @Valid DeleteUserRequestDto request) {
        userService.deleteUser(id, request.getPassword());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
