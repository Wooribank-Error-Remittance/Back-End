package com.wooribank.backend.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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

}
