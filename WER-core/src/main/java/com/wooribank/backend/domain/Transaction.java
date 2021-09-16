package com.wooribank.backend.domain;

import com.wooribank.backend.vo.AccountVo;
import com.wooribank.backend.vo.TransactionVo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;

    @Column(name = "time_of_occurrence")
    private LocalDateTime timeOfOccurrence;

    @Column(name = "sender_name")
    private String senderName;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "sending_method")
    private String sendingMethod;

    @Column(name = "receivingMethod")
    private String receivingMethod;

    @Column(name = "amount")
    private double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sent_account_id")
    private Account sentAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "received_account_id")
    private Account receivedAccount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "return_request_id")
    private ReturnRequest returnRequest;

    public Transaction(LocalDateTime timeOfOccurrence, String senderName, String receiverName, String sendingMethod,
                       String receivingMethod, double amount, Account sentAccount, Account receivedAccount) {
        this.timeOfOccurrence = timeOfOccurrence;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.sendingMethod = sendingMethod;
        this.receivingMethod = receivingMethod;
        this.amount = amount;
        this.sentAccount = sentAccount;
        this.receivedAccount = receivedAccount;
    }

    public TransactionVo toVo(String infoTarget) {
        return TransactionVo.builder()
                .id(id)
                .infoTarget(infoTarget)
                .timeOfOccurrence(timeOfOccurrence)
                .senderName(senderName)
                .receiverName(receiverName)
                .sendingMethod(sendingMethod)
                .receivingMethod(receivingMethod)
                .amount(amount)
                .sentAccountId(sentAccount.getId())
                .receivedAccountId(receivedAccount.getId())
                .isreturnRequested(returnRequest != null)
                .build();
    }

    public static List<TransactionVo> toVoList(List<Transaction> sentTransactionHistory, List<Transaction> receivedTransactionHistory) {

        final List<TransactionVo> sentTransactionVoList = sentTransactionHistory.stream().map((transaction) -> transaction.toVo("sender")).collect(Collectors.toList());

        final List<TransactionVo> receivedTransactionVoList = receivedTransactionHistory.stream().map((transaction) -> transaction.toVo("receiver")).collect(Collectors.toList());

        List<TransactionVo> totalTransactionVoList = new ArrayList<>();

        totalTransactionVoList.addAll(sentTransactionVoList);
        totalTransactionVoList.addAll(receivedTransactionVoList);

        totalTransactionVoList.sort(Comparator.comparing(TransactionVo::getTimeOfOccurrence));

        return totalTransactionVoList;
    }
}
