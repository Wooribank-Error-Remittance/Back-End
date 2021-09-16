package com.wooribank.backend.dto;

import com.wooribank.backend.vo.AccountVo;
import com.wooribank.backend.vo.TransactionVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@ToString
public class TransactionDto {

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

    public static TransactionDto of(TransactionVo vo) {
        return builder()
                .id(vo.getId())
                .infoTarget(vo.getInfoTarget())
                .timeOfOccurrence(vo.getTimeOfOccurrence())
                .senderName(vo.getSenderName())
                .receiverName(vo.getReceiverName())
                .sendingMethod(vo.getSendingMethod())
                .receivingMethod(vo.getReceivingMethod())
                .amount(vo.getAmount())
                .sentAccountId(vo.getSentAccountId())
                .receivedAccountId(vo.getReceivedAccountId())
                .isreturnRequested(vo.getIsreturnRequested())
                .build();
    }

}
