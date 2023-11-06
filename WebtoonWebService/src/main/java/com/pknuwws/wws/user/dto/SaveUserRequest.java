package com.pknuwws.wws.user.dto;

import com.pknuwws.wws.user.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveUserRequest {
    private String userId;
    private String password;
    private String nickName;
    private Gender gender;
    private LocalDate birthDate;
}
