package com.wooribank.backend.controller;

import com.wooribank.backend.component.CommonResponseMaker;
import com.wooribank.backend.dto.*;
import com.wooribank.backend.service.AccountService;
import com.wooribank.backend.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TransactionController extends AppApiV1Controller {

    private final TransactionService transactionService;
    private final CommonResponseMaker commonResponseMaker;

    @GetMapping("/transactions")
    public CommonResponse<GetTransactionListResponseDto> GetTransactionListPerMonth(
            @RequestParam final long accountId, @RequestParam final int year, @RequestParam final int month)
            throws IOException {

        GetTransactionListResponseDto responseDto = GetTransactionListResponseDto.of(
                transactionService.getTransactionListPerMonth(accountId, year, month));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }
}
