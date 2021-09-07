package com.wooribank.backend.dto;

import com.wooribank.backend.vo.RemitRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class RemitRequestDto {
//    private final Long sndBnkCd; //TO-DO : 타은행이 가능해지면 Bank entity사용해서 주석을 풀어서 개발하면됨
    private final String sndBnkNo; //Account.number
//    private final Long rcvBnkCd;
    private final String rcvBnkNo; //Account.number
    private final double amt;

    public RemitRequestVo toVo() {
        return RemitRequestVo.builder()
//                .sndBnkCd(sndBnkCd)
                .sndBnkNo(sndBnkNo)
//                .rcvBnkCd(rcvBnkCd)
                .rcvBnkNo(rcvBnkNo)
                .amt(amt)
                .build();
    }
}
