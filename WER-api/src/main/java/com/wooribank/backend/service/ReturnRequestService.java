package com.wooribank.backend.service;

import com.wooribank.backend.constant.ResponseCode;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReturnRequestService {
    private final FCMService fcmService;
    private final WooriUserRepository wooriUserRepository;
    private final TransactionRepository transactionRepository;
    private final ReturnRequestRepository returnRequestRepository;

    @Transactional
    public Void makeReturnRequest(final MakeReturnRequestRequestVo requestVo) throws IOException {

        final Optional<Transaction> transactionOptional = transactionRepository.findTopById(requestVo.getTransactionId());

        final Transaction transaction = transactionOptional.orElseThrow(() ->
                new CommonException(ResponseCode.TRANSACTION_NOT_EXISTED));

        final ReturnRequest returnRequest = new ReturnRequest(requestVo.getMessage(), false, false,
                transaction.getSentAccount().getWooriUser(), transaction.getReceivedAccount().getWooriUser(),
                transaction.getSentAccount(), transaction.getReceivedAccount(), transaction);

        returnRequestRepository.save(returnRequest);

        final String receiverToken = returnRequest.getReceivedUser().getFcmToken().getToken();

        if (receiverToken!=null) {
            fcmService.sendMessageTo(receiverToken,"착오송금액 반환 요청 알림", transaction.getSenderName()+"님이 착오송금액 반환을 요청하셨습니다.");
        }

        return null;
    }

    @Transactional(readOnly = true)
    public GetReceivedReturnRequestsResponseVo getReceivedReturnRequest(final String userId) {
        final Optional<WooriUser> wooriUserOptional =
                wooriUserRepository.findTopByUserId(userId);

        final WooriUser wooriUser =
                wooriUserOptional.orElseThrow(() -> new CommonException(ResponseCode.USER_NOT_EXISTED));

        final List<ReturnRequest> receivedReturnRequests =
                returnRequestRepository.findAllByReceivedUserOrderByCreatedAt(wooriUser);

        final List<ReturnRequestVo> returnRequestVoList = ReturnRequest.toVoList(receivedReturnRequests);

        return GetReceivedReturnRequestsResponseVo.builder().receivedReturnRequestList(returnRequestVoList).build();
    }

    @Transactional(readOnly = true)
    public GetSentReturnRequestsResponseVo getSentReturnRequest(final String userId) {
        final Optional<WooriUser> wooriUserOptional =
                wooriUserRepository.findTopByUserId(userId);

        final WooriUser wooriUser =
                wooriUserOptional.orElseThrow(() -> new CommonException(ResponseCode.USER_NOT_EXISTED));

        final List<ReturnRequest> sentReturnRequests =
                returnRequestRepository.findAllBySentUserOrderByCreatedAt(wooriUser);

        final List<ReturnRequestVo> returnRequestVoList = ReturnRequest.toVoList(sentReturnRequests);

        return GetSentReturnRequestsResponseVo.builder().sentReturnRequestList(returnRequestVoList).build();
    }

    @Transactional
    public Void acceptReturnRequest(final Long returnRequestId) throws IOException {

        final Optional<ReturnRequest> returnRequestOptional = returnRequestRepository.findTopById(returnRequestId);

        final ReturnRequest returnRequest = returnRequestOptional.orElseThrow(() ->
                new CommonException(ResponseCode.RETURN_REQUEST_NOT_EXISTED));

        returnRequest.accept();

        final Transaction transaction = new Transaction(LocalDateTime.now(),
                returnRequest.getTransaction().getReceiverName(), returnRequest.getTransaction().getSenderName(),
                "착오송금반환", "착오송금반환", returnRequest.getTransaction().getAmount(),
                returnRequest.getReceivedAccount(), returnRequest.getSentAccount());

        transactionRepository.save(transaction);

        return null;
    }
}
