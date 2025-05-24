package com.sparta.schedule.auth.service;

import com.sparta.schedule.user.dto.SignUpResponseDto;
import com.sparta.schedule.user.entity.User;
import com.sparta.schedule.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public User login(String email, String password) {
        User user = userRepository.findByEmailOrElseThrow(email);

        if (!user.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 일치하지 않습니다.");
        }

        return user;
    }

    /**
     * 회원가입
     *
     * @param username 사용자 이름
     * @param password 사용자 비밀번호
     * @param email    사용자 이메일
     * @return 저장된 사용자 정보를 담은 SignUpResponseDto
     */
    public SignUpResponseDto signUp(String username, String password, String email) {
        User user = new User(username, password, email);

        User savedUser = userRepository.save(user);

        return SignUpResponseDto.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .createdAt(savedUser.getCreatedAt())
                .updatedAt(savedUser.getUpdatedAt())
                .build();
    }
}
