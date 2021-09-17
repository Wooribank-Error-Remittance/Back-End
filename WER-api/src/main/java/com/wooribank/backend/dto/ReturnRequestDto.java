package com.wooribank.backend.dto;

import com.wooribank.backend.vo.ReturnRequestVo;
import com.wooribank.backend.vo.TransactionVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@ToString
public class ReturnRequestDto {

    private Long id;
    private String sentUserName;
    private String sentAccountNumber;
    private String receivedUserName;
    private String receivedAccountNumber;
    private String message;
    private LocalDateTime transactionTime;
    private boolean isReported;
    private boolean isConcluded;
    private double amount;

    public static ReturnRequestDto of(ReturnRequestVo vo) {
        return builder()
                .id(vo.getId())
                .sentUserName(vo.getSentUserName())
                .sentAccountNumber(vo.getSentAccountNumber())
                .receivedUserName(vo.getReceivedUserName())
                .receivedAccountNumber(vo.getReceivedAccountNumber())
                .message(vo.getMessage())
                .transactionTime(vo.getTransactionTime())
                .isReported(vo.isReported())
                .isConcluded(vo.isConcluded())
                .amount(vo.getAmount())
                .build();
    }
}
