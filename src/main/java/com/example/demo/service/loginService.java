package com.example.demo.service;


import com.example.demo.domain.User;
import com.example.demo.dto.UserDto;
import com.example.demo.enums.UserRole;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class loginService {

    private final UserRepository userRepository;

    // 로그인 - 이메일로 사용자 조회 후 DTO 반환
    public UserDto login(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .lastUpdatedAt(user.getLastUpdatedAt())
                .build();
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .name(user.getName())
                        .role(user.getRole())
                        .studentNumber(user.getStudentNumber())
                        .department(user.getDepartment())
                        .createdAt(user.getCreatedAt())
                        .lastUpdatedAt(user.getLastUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole())
                .studentNumber(user.getStudentNumber())
                .department(user.getDepartment())
                .createdAt(user.getCreatedAt())
                .lastUpdatedAt(user.getLastUpdatedAt())
                .build();
    }

    public void deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        userRepository.delete(user);
    }

    @Transactional
    public UserDto signup(String email, String name, String role, String password, String stdNum, String depart) {

        UserRole userRole = UserRole.valueOf(role.toUpperCase());

        User newUser = User.builder()
                .email(email)
                .name(name)
                .password(password)           // 비밀번호 암호화가 필요한 경우, 별도 처리
                .role(userRole)
                .studentNumber(stdNum)
                .department(depart)
                .build();

        // 3. 저장
        User savedUser = userRepository.save(newUser);

        // 4. DTO 변환 및 반환
        return UserDto.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .name(savedUser.getName())
                .role(savedUser.getRole())
                .studentNumber(savedUser.getStudentNumber())
                .department(savedUser.getDepartment())
                .createdAt(savedUser.getCreatedAt())
                .lastUpdatedAt(savedUser.getLastUpdatedAt())
                .build();
    }
}
