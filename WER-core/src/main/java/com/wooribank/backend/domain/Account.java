package com.wooribank.backend.domain;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private double balance;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "woori_user_id")
    private WooriUser wooriUser;
}
