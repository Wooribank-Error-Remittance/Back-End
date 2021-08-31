package com.wooribank.backend.dto;

import com.wooribank.backend.vo.SignInRequestVo;
import com.wooribank.backend.vo.SignUpRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class SignInRequestDto {
    private final String userId;
    private final String password;

    public SignInRequestVo toVo() {
        return SignInRequestVo.builder()
                .userId(userId)
                .password(password)
                .build();
    }
}
