package com.wooribank.backend.controller;

import com.wooribank.backend.component.CommonResponseMaker;
import com.wooribank.backend.dto.*;
import com.wooribank.backend.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AccountController extends AppApiV1Controller {

    private final AccountService accountService;
    private final CommonResponseMaker commonResponseMaker;

    @PostMapping("/accounts/update")
    public CommonResponse<UpdateAllAccountResponseDto> UpdateAllAccounts(
            @RequestBody UpdateAllAccountRequestDto requestDto) throws IOException {

        UpdateAllAccountResponseDto responseDto = UpdateAllAccountResponseDto.of(
                accountService.updateAllAccounts(requestDto.toVo()));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @GetMapping("/accounts")
    public CommonResponse<GetAccountListResponseDto> GetAccountList(
            @RequestBody GetAccountListRequestDto requestDto) throws IOException {

        GetAccountListResponseDto responseDto = GetAccountListResponseDto.of(
                accountService.getAccountList(requestDto.toVo()));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }
}
