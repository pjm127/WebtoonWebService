package com.pknuwws.wws.user.application;

import com.pknuwws.wws.user.domain.User;
import com.pknuwws.wws.user.dto.SaveUserRequest;
import com.pknuwws.wws.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Long join(SaveUserRequest request) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User saveUser = User.builder()
                .userId(request.getUserId())
                .birthDate(request.getBirthDate())
                .nickName(request.getNickName())
                .password(passwordEncoder.encode(request.getPassword()))
                .gender(request.getGender())
                .build();
        userRepository.save(saveUser);
        return saveUser.getId();

    }
}
