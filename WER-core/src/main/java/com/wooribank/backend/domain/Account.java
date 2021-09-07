package com.wooribank.backend.domain;

import com.wooribank.backend.vo.AccountVo;
import com.wooribank.backend.vo.WooriUserVo;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "account")
public class Account  extends BaseTimeEntity {

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

    @Builder
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

    public void update(double balance){
        this.balance = balance;
    }

//    @OneToMany(mappedBy = "account_prgr_step_his")
//    private List<AccountPrgrStepHis> accountPrgrStepHisList = new ArrayList<>();

//    public List<AccountPrgrStepHis> toAccountPrgrStepHisList() {
//        return accountPrgrStepHisList.stream().map(account -> AccountPrgrStepHis.toVo()).collect(Collectors.toList());
//    }

}
