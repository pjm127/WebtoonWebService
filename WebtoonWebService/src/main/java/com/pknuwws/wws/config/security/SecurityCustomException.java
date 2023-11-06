package com.pknuwws.wws.config.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SecurityCustomException extends RuntimeException{
    private final JwtExceptionCode jwtExceptionCode;
}
