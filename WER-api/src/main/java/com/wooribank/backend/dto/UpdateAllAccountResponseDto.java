package com.wooribank.backend.dto;

import com.wooribank.backend.dto.LoadAccountResponseDto.GRID;
import com.wooribank.backend.vo.UpdateAllAccountRequestVo;
import com.wooribank.backend.vo.UpdateAllAccountResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@ToString
public class UpdateAllAccountResponseDto {
    private final List<AccountDto> accountList;

    public static UpdateAllAccountResponseDto of(UpdateAllAccountResponseVo vo){
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
