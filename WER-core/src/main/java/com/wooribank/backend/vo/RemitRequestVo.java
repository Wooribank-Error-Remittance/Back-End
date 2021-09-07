package com.wooribank.backend.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class RemitRequestVo {
//    private final Long sndBnkCd;
    private final String sndBnkNo;
//    private final Long rcvBnkCd;
    private final String rcvBnkNo;
    private final double amt;

}
