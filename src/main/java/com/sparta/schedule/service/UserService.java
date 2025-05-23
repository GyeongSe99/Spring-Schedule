package com.sparta.schedule.service;

import com.sparta.schedule.dto.SignUpResponseDto;
import com.sparta.schedule.dto.UserResponseDto;
import com.sparta.schedule.entity.User;
import com.sparta.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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

    /**
     * 사용자 ID로 사용자 정보를 조회
     *
     * @param id 조회할 사용자 ID
     * @return 사용자 정보를 담은 UserResponseDto
     */
    public UserResponseDto findById(Long id) {

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    /**
     * 전체 유저 조회
     *
     * @return 여러명의 사용자 정보를 담은 리스트 형태의 UserResponseDto
     */
    public List<UserResponseDto> findAll() {

        List<User> result = userRepository.findAll();

        return result.stream()
                .map(user -> UserResponseDto.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .createdAt(user.getCreatedAt())
                        .updatedAt(user.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
