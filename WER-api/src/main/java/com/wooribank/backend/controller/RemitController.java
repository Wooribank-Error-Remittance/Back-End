package com.wooribank.backend.controller;

import com.wooribank.backend.component.CommonResponseMaker;
import com.wooribank.backend.domain.Account;
import com.wooribank.backend.dto.AccountDto;
import com.wooribank.backend.dto.CommonResponse;
import com.wooribank.backend.dto.RemitRequestDto;
import com.wooribank.backend.dto.WooriUserDto;
import com.wooribank.backend.service.RemitService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RemitController extends AppApiV1Controller {

    private final RemitService remitService;
    private final PasswordEncoder passwordEncoder;
    private final CommonResponseMaker commonResponseMaker;

    @PostMapping("/remit")
    public CommonResponse<AccountDto> completeRemit(@RequestBody RemitRequestDto requestDto) throws IOException {

        final AccountDto responseDto = AccountDto.of(remitService.remit(requestDto.toVo()));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

}