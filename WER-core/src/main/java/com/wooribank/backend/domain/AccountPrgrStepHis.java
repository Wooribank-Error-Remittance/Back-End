package com.wooribank.backend.domain;

import com.wooribank.backend.vo.AccountPrgrStepHisVo;
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
@Table(name = "account_prgr_step_his")
public class AccountPrgrStepHis extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_prgr_step_his_id")
    private Long id;

//    @Column(name = "number")
//    private String number;

    @Column(name = "before_balance")
    private double beforeBalance;

    @Column(name = "after_balance")
    private double afterBalance;

    @Column(name = "amount")
    private double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "number") //account_id로 해야할까요?
    private Account account;

    public void setAccount(Account account) {
        this.account = account;
    }

    public AccountPrgrStepHis(double beforeBalance,double afterBalance,double amount) {
        this.beforeBalance = beforeBalance;
        this.afterBalance = afterBalance;
        this.amount = amount;
    }

    public AccountPrgrStepHisVo toVo() {
        return AccountPrgrStepHisVo.builder()
                .id(id)
                .beforeBalance(beforeBalance)
                .afterBalance(afterBalance)
                .amount(amount)
                .build();
    }
}
