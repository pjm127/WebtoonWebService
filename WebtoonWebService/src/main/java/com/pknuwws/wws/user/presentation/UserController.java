package com.pknuwws.wws.user.presentation;

import com.pknuwws.wws.user.application.UserService;
import com.pknuwws.wws.user.dto.SaveUserRequest;
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
    public ResponseEntity<Long> join(@RequestBody SaveUserRequest request) {
        return ResponseEntity.ok(userService.join(request));
    }

    @GetMapping("/check-id")
    public ResponseEntity<String> validateDuplicateUserId(@RequestParam String userId) {
        userService.validateDuplicateUserId(userId);
        return ResponseEntity.ok("중복되는 아이디 없음");
    }
    @GetMapping("/check-nickname")
    public ResponseEntity<String> validateDuplicateNickName(@RequestParam String nickName) {
        userService.validateDuplicateNickName(nickName);
        return ResponseEntity.ok("중복되는 닉네임 없음");
    }
}
