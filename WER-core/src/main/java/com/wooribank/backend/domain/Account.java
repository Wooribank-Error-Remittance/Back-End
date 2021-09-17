package com.wooribank.backend.domain;

import com.wooribank.backend.vo.AccountVo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "woori_user_id")
    private WooriUser wooriUser;

    @OneToMany(mappedBy = "sentAccount")
    private List<Transaction> sentTransactionHistory;

    @OneToMany(mappedBy = "receivedAccount")
    private List<Transaction> receivedTransactionHistory;

    @OneToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    public Account(String number, String name, double balance, WooriUser wooriUSer, Bank bank) {
        this.number = number;
        this.name = name;
        this.balance = balance;
        this.wooriUser = wooriUSer;
        this.bank = bank;
    }

    public void setUser(WooriUser wooriUser) {
        wooriUser.getAccountList().add(this);
    }

    public AccountVo toVo() {
        return AccountVo.builder()
                .id(id)
                .bank(bank.getName())
                .number(number)
                .name(name)
                .balance(balance)
                .build();
    }
}
