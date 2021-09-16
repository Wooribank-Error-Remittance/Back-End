package com.wooribank.backend.vo;

import com.wooribank.backend.domain.Account;
import com.wooribank.backend.domain.ReturnRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@ToString
public class TransactionVo {

    private Long id;
    private String infoTarget;
    private LocalDateTime timeOfOccurrence;
    private String senderName;
    private String receiverName;
    private String sendingMethod;
    private String receivingMethod;
    private double amount;
    private Long sentAccountId;
    private Long receivedAccountId;
    private Boolean isreturnRequested;

}
