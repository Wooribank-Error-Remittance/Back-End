package com.wooribank.backend.domain;

import com.wooribank.backend.vo.AccountVo;
import com.wooribank.backend.vo.WooriUserVo;
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

    public Account(String number, String name, double balance) {
        this.number = number;
        this.name = name;
        this.balance = balance;
    }

    public void setUser(WooriUser wooriUser) {
        this.wooriUser = wooriUser;
        wooriUser.getAccountList().add(this);
    }

    public AccountVo toVo() {
        return AccountVo.builder()
                .id(id)
                .number(number)
                .name(name)
                .balance(balance)
                .build();
    }
}
