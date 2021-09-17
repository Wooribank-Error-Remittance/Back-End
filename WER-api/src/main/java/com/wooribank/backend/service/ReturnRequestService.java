package com.wooribank.backend.service;

import com.wooribank.backend.constant.ResponseCode;
import com.wooribank.backend.domain.Bank;
import com.wooribank.backend.domain.ReturnRequest;
import com.wooribank.backend.domain.Transaction;
import com.wooribank.backend.domain.WooriUser;
import com.wooribank.backend.exception.CommonException;
import com.wooribank.backend.repository.*;
import com.wooribank.backend.vo.GetReceivedReturnRequestsResponseVo;
import com.wooribank.backend.vo.GetSentReturnRequestsResponseVo;
import com.wooribank.backend.vo.MakeReturnRequestRequestVo;
import com.wooribank.backend.vo.ReturnRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReturnRequestService {
    private final WooriUserRepository wooriUserRepository;
    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;
    private final TransactionRepository transactionRepository;
    private final ReturnRequestRepository returnRequestRepository;
    private final RestTemplate restTemplate;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Void makeReturnRequest(final MakeReturnRequestRequestVo requestVo) throws IOException {

        final Optional<Transaction> transactionOptional = transactionRepository.findTopById(requestVo.getTransactionId());

        final Transaction transaction = transactionOptional.orElseThrow(() ->
                new CommonException(ResponseCode.TRANSACTION_NOT_EXISTED));

        final ReturnRequest returnRequest = new ReturnRequest(requestVo.getMessage(), false, false,
                transaction.getReturnRequest().getSentUser(), transaction.getReturnRequest().getReceivedUser(),
                transaction.getSentAccount(), transaction.getReceivedAccount(), transaction);

        returnRequestRepository.save(returnRequest);

        return null;
    }

    @Transactional(readOnly = true)
    public GetReceivedReturnRequestsResponseVo getReceivedReturnRequest(final long id) {
        final Optional<WooriUser> wooriUserOptional =
                wooriUserRepository.findTopById(id);

        final WooriUser wooriUser =
                wooriUserOptional.orElseThrow(() -> new CommonException(ResponseCode.USER_NOT_EXISTED));

        final List<ReturnRequest> receivedReturnRequests =
                returnRequestRepository.findAllByReceivedUserOrderByCreatedAt(wooriUser);

        final List<ReturnRequestVo> returnRequestVoList = ReturnRequest.toVoList(receivedReturnRequests);

        return GetReceivedReturnRequestsResponseVo.builder().receivedReturnRequestList(returnRequestVoList).build();
    }

    @Transactional(readOnly = true)
    public GetSentReturnRequestsResponseVo getSentReturnRequest(final long id) {
        final Optional<WooriUser> wooriUserOptional =
                wooriUserRepository.findTopById(id);

        final WooriUser wooriUser =
                wooriUserOptional.orElseThrow(() -> new CommonException(ResponseCode.USER_NOT_EXISTED));

        final List<ReturnRequest> sentReturnRequests =
                returnRequestRepository.findAllBySentUserOrderByCreatedAt(wooriUser);

        final List<ReturnRequestVo> returnRequestVoList = ReturnRequest.toVoList(sentReturnRequests);

        return GetSentReturnRequestsResponseVo.builder().sentReturnRequestList(returnRequestVoList).build();
    }
}
