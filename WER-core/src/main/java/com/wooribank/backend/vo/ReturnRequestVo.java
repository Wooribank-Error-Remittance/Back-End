package com.wooribank.backend.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@ToString
public class ReturnRequestVo {

    private Long id;
    private String sentUserName;
    private String sentAccountNumber;
    private String receivedUserName;
    private String receivedAccountNumber;
    private LocalDateTime transactionTime;
    private boolean isReported;
    private boolean isConcluded;
    private double amount;

}
