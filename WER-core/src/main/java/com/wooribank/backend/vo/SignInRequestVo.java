package com.wooribank.backend.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class SignInRequestVo {
    private final String userId;
    private final String password;
}
