package com.wooribank.backend.dto;

import com.wooribank.backend.vo.GetAccountListRequestVo;
import com.wooribank.backend.vo.UpdateAllAccountRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class GetAccountListRequestDto {
    private final String userId;
    private final String password;

    public GetAccountListRequestVo toVo() {
        return GetAccountListRequestVo.builder()
                .userId(userId)
                .password(password)
                .build();
    }
}
