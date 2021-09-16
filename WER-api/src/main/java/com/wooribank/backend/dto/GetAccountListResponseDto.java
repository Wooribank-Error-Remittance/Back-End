package com.wooribank.backend.dto;

import com.wooribank.backend.vo.GetAccountListResponseVo;
import com.wooribank.backend.vo.UpdateAllAccountResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@ToString
public class GetAccountListResponseDto {
    private final List<AccountDto> accountList;

    public static GetAccountListResponseDto of(GetAccountListResponseVo vo){
        return builder()
                .accountList(
                        vo.getAccountList()
                                .stream()
                                .map(accountVo -> AccountDto.of(accountVo))
                                .collect(Collectors.toList())
                )
                .build();
    }
}
