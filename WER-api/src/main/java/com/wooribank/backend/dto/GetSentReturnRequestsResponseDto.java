package com.wooribank.backend.dto;

import com.wooribank.backend.vo.GetSentReturnRequestsResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@ToString
public class GetSentReturnRequestsResponseDto {
    private final List<ReturnRequestDto> sentReturnRequestList;

    public static GetSentReturnRequestsResponseDto of(GetSentReturnRequestsResponseVo vo){
        return builder()
                .sentReturnRequestList(
                        vo.getSentReturnRequestList()
                                .stream()
                                .map(returnRequestVo -> ReturnRequestDto.of(returnRequestVo))
                                .collect(Collectors.toList())
                )
                .build();
    }
}
