package com.wooribank.backend.domain;

import com.wooribank.backend.vo.AccountVo;
import com.wooribank.backend.vo.WooriUserVo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "woori_user")
public class WooriUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "woori_user_id")
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "wooriUser")
    private List<Account> accountList = new ArrayList<>();

    @OneToMany(mappedBy = "sentUser")
    private List<ReturnRequest> sentReturnRequestList = new ArrayList<>();

    @OneToMany(mappedBy = "receivedUser")
    private List<ReturnRequest> receivedReturnRequestList = new ArrayList<>();

    @OneToOne(mappedBy = "wooriUser", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private FCMToken fcmToken;

    public WooriUser(String userId, String password, String name, String phoneNumber) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Boolean hasToken() {
        return fcmToken!=null;
    }

    public WooriUserVo toVo() {
        return WooriUserVo.builder()
                .wooriUserId(id)
                .name(name)
                .userId(userId)
                .phoneNumber(phoneNumber)
                .build();
    }

    public void registerToken(FCMToken fcmToken) {
        this.fcmToken=fcmToken;
    }

    public List<AccountVo> toAccountList() {
        return accountList.stream().map(account -> account.toVo()).collect(Collectors.toList());
    }
}
