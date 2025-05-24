package com.example.demo.service;

public class loginService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserDto signup(UserSignupRequest request) {
        // 이메일 중복 확인
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        // 엔티티 생성
        User user = User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .role(request.getRole() != null ? request.getRole() : UserRole.USER)
                .password(passwordEncoder.encode(request.getPassword()))
                .createdAt(LocalDateTime.now())
                .lastUpdatedAt(LocalDateTime.now())
                .build();

        // 저장
        User saved = userRepository.save(user);

        // DTO 반환
        return UserDto.builder()
                .id(saved.getId())
                .email(saved.getEmail())
                .name(saved.getName())
                .role(saved.getRole())
                .createdAt(saved.getCreatedAt())
                .lastUpdatedAt(saved.getLastUpdatedAt())
                .build();
    }
}
