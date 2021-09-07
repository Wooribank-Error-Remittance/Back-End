package com.wooribank.backend.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class AccountPrgrStepHisVo {
    private Long id;
    private double beforeBalance;
    private double afterBalance;
    private double amount;
}
