package com.sparta.schedule.auth.controller;

import com.sparta.schedule.auth.dto.LoginRequestDto;
import com.sparta.schedule.auth.service.AuthService;
import com.sparta.schedule.auth.dto.SignUpRequestDto;
import com.sparta.schedule.auth.dto.SignUpResponseDto;
import com.sparta.schedule.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequestDto request, HttpServletRequest httpServletRequest) {

        User user = authService.login(request.getEmail(), request.getPassword());
        httpServletRequest.getSession().setAttribute("userId", user.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto request) {

        SignUpResponseDto response = authService.signUp(
                request.getUsername(),
                request.getPassword(),
                request.getEmail()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate(); // 세션 무효화 → 로그아웃
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
