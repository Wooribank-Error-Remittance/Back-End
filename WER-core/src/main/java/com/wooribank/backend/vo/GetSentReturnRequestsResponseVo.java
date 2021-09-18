package com.wooribank.backend.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class GetSentReturnRequestsResponseVo {
    private final List<ReturnRequestVo> sentReturnRequestList;
}
