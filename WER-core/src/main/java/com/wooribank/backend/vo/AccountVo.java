package com.wooribank.backend.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class AccountVo {
    private Long id;
    private String number;
    private String name;
    private double balance;
}
