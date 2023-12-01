package com.pknuwws.wws.user.presentation;

import com.pknuwws.wws.user.application.UserService;
import com.pknuwws.wws.user.dto.AuthenticationResponse;
import com.pknuwws.wws.user.dto.LoginRequest;
import com.pknuwws.wws.user.dto.SaveUserRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    @Operation(summary = "회원 가입")
    public ResponseEntity<String> signup(@RequestBody SaveUserRequest userSignUpDto) {
        userService.join(userSignUpDto);
        return ResponseEntity.ok("회원가입 완료");
    }

    @PostMapping("/login")
    @Operation(summary = "로그인")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }

    @GetMapping("/check-id")
    @Operation(summary = "아이디 중복 체크")
    public ResponseEntity<String> validateDuplicateUserId(@RequestParam String userId) {
        userService.validateDuplicateUserId(userId);
        return ResponseEntity.ok("중복되는 아이디 없음");
    }
    @GetMapping("/check-nickname")
    @Operation(summary = "닉네임 중복 체크")
    public ResponseEntity<String> validateDuplicateNickName(@RequestParam String nickName) {
        userService.validateDuplicateNickName(nickName);
        return ResponseEntity.ok("중복되는 닉네임 없음");
    }


}
