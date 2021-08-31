package com.wooribank.backend.domain;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "woori_user")
public class WooriUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "woori_user_id")
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "wooriUser")
    private List<Account> accountList = new ArrayList<>();
}
