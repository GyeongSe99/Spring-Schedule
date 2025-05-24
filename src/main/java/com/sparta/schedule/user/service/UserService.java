package com.sparta.schedule.user.service;

import com.sparta.schedule.user.dto.SignUpResponseDto;
import com.sparta.schedule.user.dto.UserResponseDto;
import com.sparta.schedule.user.entity.User;
import com.sparta.schedule.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

        User user = userRepository.findByIdOrElseThrow(id);

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

    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {

        User findUser = userRepository.findByIdOrElseThrow(id);

        this.verifyPasswordMatch(oldPassword, findUser.getPassword());

        findUser.updatePassword(newPassword);
    }

    public void deleteUser(Long id, String inputPassword) {

        User findUser = userRepository.findByIdOrElseThrow(id);

        this.verifyPasswordMatch(inputPassword, findUser.getPassword());

        userRepository.deleteById(id);
    }

    /**
     * 입력받은 비밀번호와 기존의 비밀번호의 일치여부에 따른 예외 처리
     *
     * @param inputPassword 입력받은 비밀번호
     * @param savedPassword 기존의 비밀번호
     */
    private void verifyPasswordMatch(String inputPassword, String savedPassword) {
        if(!inputPassword.equals(savedPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
    }
}
