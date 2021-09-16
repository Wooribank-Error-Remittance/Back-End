package com.wooribank.backend.dto;

import com.wooribank.backend.vo.GetAccountListResponseVo;
import com.wooribank.backend.vo.GetTransactionListResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@ToString
public class GetTransactionListResponseDto {
    private final List<TransactionDto> transactionList;

    public static GetTransactionListResponseDto of(GetTransactionListResponseVo vo){
        return builder()
                .transactionList(
                        vo.getTransactionList()
                                .stream()
                                .map(transactionVo -> TransactionDto.of(transactionVo))
                                .collect(Collectors.toList())
                )
                .build();
    }
}
