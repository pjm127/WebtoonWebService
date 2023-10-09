package com.pknuwws.wws.user.application;

import com.pknuwws.wws.config.security.JwtTokenProvider;
import com.pknuwws.wws.exception.CustomException;
import com.pknuwws.wws.exception.ResponseCode;
import com.pknuwws.wws.user.domain.User;
import com.pknuwws.wws.user.dto.AuthenticationResponse;
import com.pknuwws.wws.user.dto.LoginRequest;
import com.pknuwws.wws.user.dto.SaveUserRequest;
import com.pknuwws.wws.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.pknuwws.wws.exception.ResponseCode.USER_ID_DUPLICATE;
import static com.pknuwws.wws.exception.ResponseCode.USER_NICKNAME_DUPLICATE;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    //회원가입
    public Long join(SaveUserRequest request) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User saveUser = User.builder()
                .loginId(request.getUserId())
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
        userRepository.findByLoginId(userId)
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
    public AuthenticationResponse login(LoginRequest loginRequest) {
        log.info("userid is : {}" , loginRequest.getUserId());
        log.info("password is : {}" , loginRequest.getPassword());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword()));

        User user = userRepository.findByLoginId(loginRequest.getUserId()).orElseThrow(()-> new IllegalArgumentException("아이디가 존재하지 않습니다."));

        log.info("user is : {}" , user.getLoginId());
        String accessToken = jwtTokenProvider.generateToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);

        log.info("accessToken is : {}" , accessToken);
        log.info("refreshToken is : {}" , refreshToken);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getLoginId())
                .build();
    }

}

