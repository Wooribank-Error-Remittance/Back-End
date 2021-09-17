package com.wooribank.backend.dto;

import com.wooribank.backend.vo.MakeReturnRequestRequestVo;
import com.wooribank.backend.vo.SignInRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class MakeReturnRequestRequestDto {
    private final long transactionId;
    private final String message;

    public MakeReturnRequestRequestVo toVo() {
        return MakeReturnRequestRequestVo.builder()
                .transactionId(transactionId)
                .message(message)
                .build();
    }
}