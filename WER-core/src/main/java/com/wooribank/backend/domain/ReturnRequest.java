package com.wooribank.backend.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "return_request")
public class ReturnRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "return_request_id")
    private Long id;

    @Column(name = "message")
    private String message;

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

    @OneToOne(mappedBy = "returnRequest")
    private Transaction transaction;
}
