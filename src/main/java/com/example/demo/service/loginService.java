package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDto;
import com.example.demo.enums.UserRole;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

public class loginService {

//    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder passwordEncoder;

//    public UserDto signup(String email, String password) {
//        if (userRepository.existsByEmail(email)) {
//            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
//        }
//
//        String encodedPassword = passwordEncoder.encode(password);
//
//        // 3. 엔티티 생성
//        User user = User.builder()
//                .email(email)
//                .name("신규회원") // 또는 별도 입력 받도록 변경 가능
//                .role(UserRole.USER)
//                .password(encodedPassword)
//                .createdAt(LocalDateTime.now())
//                .lastUpdatedAt(LocalDateTime.now())
//                .build();
//
//        // 4. 저장
//        User saved = userRepository.save(user);
//
//        // 5. DTO 변환 후 반환
//        return UserDto.builder()
//                .id(saved.getId())
//                .email(saved.getEmail())
//                .name(saved.getName())
//                .role(saved.getRole())
//                .createdAt(saved.getCreatedAt())
//                .lastUpdatedAt(saved.getLastUpdatedAt())
//                .build();
//    }
}
