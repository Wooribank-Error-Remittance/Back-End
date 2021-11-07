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
    private final String reason;

    public MakeReturnRequestRequestVo toVo() {
        return MakeReturnRequestRequestVo.builder()
                .transactionId(transactionId)
                .message(message)
                .reason(reason)
                .build();
    }
}