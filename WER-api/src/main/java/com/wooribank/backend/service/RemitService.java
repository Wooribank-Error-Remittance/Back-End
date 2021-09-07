package com.wooribank.backend.service;

import com.wooribank.backend.constant.ResponseCode;
import com.wooribank.backend.domain.Account;
import com.wooribank.backend.domain.AccountPrgrStepHis;
import com.wooribank.backend.domain.WooriUser;
import com.wooribank.backend.dto.RemitRequestDto;
import com.wooribank.backend.exception.CommonException;
import com.wooribank.backend.repository.AccountPrgrStepHisRepository;
import com.wooribank.backend.repository.AccountRepository;
import com.wooribank.backend.repository.WooriUserRepository;
import com.wooribank.backend.vo.AccountVo;
import com.wooribank.backend.vo.RemitRequestVo;
import com.wooribank.backend.vo.WooriUserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class RemitService {
    private final AccountRepository accountRepository;
    private final AccountRepository accountRepository2;
    private final AccountPrgrStepHisRepository accountPrgrStepHisRepository;

    @Transactional
    public AccountVo remit(RemitRequestVo requestVo) {

        //1. 송/수신인 userId 유효한지 체크
        //2. Reciever의 계좌에 blance-amount update
        //3. if(3번 성공) Reciever의 계좌에 blance+amount update
        //4. Return 값을 뭘로 줄까?

        Account sndAccount = accountRepository.findByNumber(requestVo.getSndBnkNo());
//                .orElseThrow(() ->new CommonException(ResponseCode.ACCOUNT_NOT_EXISTED));
        Account recAccount = accountRepository2.findByNumber(requestVo.getRcvBnkNo());
//                .orElseThrow(() ->new CommonException(ResponseCode.ACCOUNT_NOT_EXISTED));

        double sndBeforeBalance =sndAccount.getBalance();
        double recBeforeBalance =recAccount.getBalance();
        double amount = requestVo.getAmt();


        sndAccount.update(sndBeforeBalance-amount);

        final AccountPrgrStepHis sndAccountPrgrStepHis = new AccountPrgrStepHis(
                sndBeforeBalance,
                sndAccount.getBalance(),
                amount);
        accountPrgrStepHisRepository.save(sndAccountPrgrStepHis);
        sndAccountPrgrStepHis.setAccount(sndAccount);


        recAccount.update(recBeforeBalance+amount);

        final AccountPrgrStepHis recAccountPrgrStepHis = new AccountPrgrStepHis(
                sndBeforeBalance,
                recAccount.getBalance(),
                amount);
        accountPrgrStepHisRepository.save(recAccountPrgrStepHis);
        recAccountPrgrStepHis.setAccount(sndAccount);

        return sndAccount.toVo();
    }
}
