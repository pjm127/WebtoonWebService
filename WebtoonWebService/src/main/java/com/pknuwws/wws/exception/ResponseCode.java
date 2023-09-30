package com.pknuwws.wws.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    OK(HttpStatus.OK, "성공"),
    USER_ID_DUPLICATE(HttpStatus.BAD_REQUEST, "이미 존재하는 아이디입니다."),
    USER_NICKNAME_DUPLICATE(HttpStatus.BAD_REQUEST, "이미 존재하는 닉네임입니다.");
    private final HttpStatus httpStatus;
    private final String detail;
}
