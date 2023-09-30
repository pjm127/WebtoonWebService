package com.pknuwws.wws.user.presentation;

import com.pknuwws.wws.user.application.UserService;
import com.pknuwws.wws.user.dto.SaveUserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
