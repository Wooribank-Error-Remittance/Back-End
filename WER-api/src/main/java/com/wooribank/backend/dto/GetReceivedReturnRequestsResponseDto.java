package com.wooribank.backend.dto;

import com.wooribank.backend.vo.GetReceivedReturnRequestsResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@ToString
public class GetReceivedReturnRequestsResponseDto {
    private final List<ReturnRequestDto> receivedReturnRequestList;

    public static GetReceivedReturnRequestsResponseDto of(GetReceivedReturnRequestsResponseVo vo){
        return builder()
                .receivedReturnRequestList(
                        vo.getReceivedReturnRequestList()
                                .stream()
                                .map(returnRequestVo -> ReturnRequestDto.of(returnRequestVo))
                                .collect(Collectors.toList())
                )
                .build();
    }
}
