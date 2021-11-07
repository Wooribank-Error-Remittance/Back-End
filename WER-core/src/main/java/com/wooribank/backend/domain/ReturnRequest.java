package com.wooribank.backend.domain;

import com.wooribank.backend.constant.ResponseCode;
import com.wooribank.backend.exception.CommonException;
import com.wooribank.backend.vo.ReturnRequestVo;
import com.wooribank.backend.vo.TransactionVo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "return_request")
public class ReturnRequest extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "return_request_id")
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "reason")
    private String reason;

    @Column(name = "isConcluded")
    private Boolean isConcluded;

    @Column(name = "isReported")
    private Boolean isReported;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sent_user_id")
    private WooriUser sentUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "received_user_id")
    private WooriUser receivedUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sent_account_id")
    private Account sentAccount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "received_account_id")
    private Account receivedAccount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    public ReturnRequest(String message,String reason, Boolean isConcluded, Boolean isReported, WooriUser sentUser,
                         WooriUser receivedUser, Account sentAccount, Account receivedAccount, Transaction transaction) {
        this.message = message;
        this.reason = reason;
        this.isConcluded = isConcluded;
        this.isReported = isReported;
        this.sentUser = sentUser;
        this.receivedUser = receivedUser;
        this.sentAccount = sentAccount;
        this.receivedAccount = receivedAccount;
        this.transaction = transaction;
    }

    public ReturnRequestVo toVo() {
        return ReturnRequestVo.builder()
                .id(id)
                .sentUserName(sentUser.getName())
                .sentAccountNumber(sentAccount.getNumber())
                .receivedUserName(receivedUser.getName())
                .receivedAccountNumber(receivedAccount.getNumber())
                .message(message)
                .reason(reason)
                .transactionTime(transaction.getTimeOfOccurrence())
                .isReported(isReported)
                .isConcluded(isConcluded)
                .amount(transaction.getAmount())
                .build();
    }

    public static List<ReturnRequestVo> toVoList(List<ReturnRequest> returnRequests) {

        final List<ReturnRequestVo> returnRequestVoList =
                returnRequests.stream().map((returnRequest) -> returnRequest.toVo()).collect(Collectors.toList());

        return returnRequestVoList;
    }

    public void accept() {
        if (isConcluded) {
            throw new CommonException(ResponseCode.RETURN_REQUEST_NOT_EXISTED);
        }
        this.isConcluded = true;
        this.sentAccount.deposit(this.transaction.getAmount());
        this.receivedAccount.withdraw(this.transaction.getAmount());
    }

    public void report() {
        this.isReported = true;
    }
}
