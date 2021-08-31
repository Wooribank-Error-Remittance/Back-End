package com.wooribank.backend.dto.LoadAccountResponseDto;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class LoadAccountList {
    Long GRID_CNT;
    ArrayList<com.wooribank.backend.dto.LoadAccountResponseDto.GRID> GRID = new ArrayList<com.wooribank.backend.dto.LoadAccountResponseDto.GRID>();
}
