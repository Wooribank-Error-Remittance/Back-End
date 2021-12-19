package com.wooribank.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.wooribank.backend.constant.ResponseCode;
import com.wooribank.backend.domain.Account;
import com.wooribank.backend.domain.Bank;
import com.wooribank.backend.domain.Transaction;
import com.wooribank.backend.domain.WooriUser;
import com.wooribank.backend.dto.GetTransactionListResponseDto;
import com.wooribank.backend.dto.LoadAccountResponseDto.LoadAccountDataBody;
import com.wooribank.backend.exception.CommonException;
import com.wooribank.backend.repository.AccountRepository;
import com.wooribank.backend.repository.BankRepository;
import com.wooribank.backend.repository.TransactionRepository;
import com.wooribank.backend.repository.WooriUserRepository;
import com.wooribank.backend.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {
    private final AccountRepository accountRepository;
    private final WooriUserRepository wooriUserRepository;
    private final TransactionRepository transactionRepository;

    @Transactional(readOnly = true)
    public GetTransactionListResponseVo getTransactionListPerMonth(final long accountId, final int year, final int month)
            throws IOException {

        final Optional<Account> accountOptional = accountRepository.findTopById(accountId);

        final Account account = accountOptional.orElseThrow(() ->
                new CommonException(ResponseCode.ACCOUNT_NOT_EXISTED));

        final List<Transaction> sentTransactionHistoryPerMonth = transactionRepository.findAllBySentAccountAndTimeOfOccurrenceBetweenOrderByTimeOfOccurrenceAsc(
                account, LocalDateTime.of(year, month, 1, 0, 0, 0, 0),
                LocalDateTime.of(year, month, 1, 0, 0, 0, 0).plusMonths(1));

        final List<Transaction> receivedTransactionHistoryPerMonth = transactionRepository.findAllByReceivedAccountAndTimeOfOccurrenceBetweenOrderByTimeOfOccurrenceAsc(
                account, LocalDateTime.of(year, month, 1, 0, 0, 0, 0),
                LocalDateTime.of(year, month, 1, 0, 0, 0, 0).plusMonths(1));

        final List<TransactionVo> transactionVoList = Transaction.toVoList(sentTransactionHistoryPerMonth, receivedTransactionHistoryPerMonth);
        return GetTransactionListResponseVo.builder().transactionList(transactionVoList).build();
    }
}
