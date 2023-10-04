package com.pknuwws.wws.user.application;

import com.pknuwws.wws.exception.CustomException;
import com.pknuwws.wws.exception.ResponseCode;
import com.pknuwws.wws.user.domain.User;
import com.pknuwws.wws.user.dto.LoginRequest;
import com.pknuwws.wws.user.dto.SaveUserRequest;
import com.pknuwws.wws.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.pknuwws.wws.exception.ResponseCode.USER_ID_DUPLICATE;
import static com.pknuwws.wws.exception.ResponseCode.USER_NICKNAME_DUPLICATE;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    //회원가입
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
    //회원가입 - 아이디 중복체크
    public void validateDuplicateUserId(String userId) {
        userRepository.findByUserId(userId)
                .ifPresent(m -> {
                    throw new CustomException(USER_ID_DUPLICATE);
                });
    }


    //회원가입 - 닉네임중복체크
    public void validateDuplicateNickName(String nickName) {
        userRepository.findByNickName(nickName)
                .ifPresent(m -> {
                    throw new CustomException(USER_NICKNAME_DUPLICATE);
                });
    }

    //로그인
    public void login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserId(),
                        loginRequest.getPassword()
                )
        );

    }

}

