package com.wooribank.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.wooribank.backend.constant.ResponseCode;
import com.wooribank.backend.domain.Account;
import com.wooribank.backend.domain.Bank;
import com.wooribank.backend.domain.WooriUser;
import com.wooribank.backend.dto.LoadAccountResponseDto.LoadAccountDataBody;
import com.wooribank.backend.dto.UpdateAllAccountResponseDto;
import com.wooribank.backend.exception.CommonException;
import com.wooribank.backend.repository.AccountRepository;
import com.wooribank.backend.repository.BankRepository;
import com.wooribank.backend.repository.WooriUserRepository;
import com.wooribank.backend.vo.GetAccountListRequestVo;
import com.wooribank.backend.vo.GetAccountListResponseVo;
import com.wooribank.backend.vo.UpdateAllAccountRequestVo;
import com.wooribank.backend.vo.UpdateAllAccountResponseVo;
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
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final WooriUserRepository wooriUserRepository;
    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;
    private final RestTemplate restTemplate;
    private final PasswordEncoder passwordEncoder;

    @Value("${wooribank.appkey}")
    private String appkey;

    @Value("${wooribank.url.wooribank-open-api}")
    private String openApiUrl;

    @Transactional
    public UpdateAllAccountResponseVo updateAllAccounts(UpdateAllAccountRequestVo requestVo) throws IOException {

        final ObjectMapper objectMapper = new ObjectMapper();

        final String requestBody = "{\n" +
                "  \"dataHeader\": {\n" +
                "    \"UTZPE_CNCT_IPAD\": \"\",\n" +
                "    \"UTZPE_CNCT_MCHR_UNQ_ID\": \"\",\n" +
                "    \"UTZPE_CNCT_TEL_NO_TXT\": \"\",\n" +
                "    \"UTZPE_CNCT_MCHR_IDF_SRNO\": \"\",\n" +
                "    \"UTZ_MCHR_OS_DSCD\": \"\",\n" +
                "    \"UTZ_MCHR_OS_VER_NM\": \"\",\n" +
                "    \"UTZ_MCHR_MDL_NM\": \"\",\n" +
                "    \"UTZ_MCHR_APP_VER_NM\": \"\"\n" +
                "  },\n" +
                "  \"dataBody\": {}\n" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("appkey", appkey);

        final String requestUrl = openApiUrl + "/finance/getIndivAllAccInfo";

        final ResponseEntity<String> responseEntity = restTemplate.exchange(
                requestUrl, HttpMethod.POST, new HttpEntity<String>(requestBody, headers), String.class);

        objectMapper.writeValueAsString(responseEntity.getBody());

        Gson gson = new Gson();
        LoadAccountDataBody loadAccountDataBody = gson.fromJson(responseEntity.getBody(), LoadAccountDataBody.class);

        final Optional<WooriUser> wooriUserOptional = wooriUserRepository.findTopByUserId(requestVo.getUserId());

        final WooriUser wooriUser = wooriUserOptional.orElseThrow(() ->
                new CommonException(ResponseCode.USER_NOT_EXISTED));

        if (!passwordEncoder.matches(requestVo.getPassword(), wooriUser.getPassword())) {
            throw new CommonException(ResponseCode.INVALID_PASSWORD);
        }

        final Optional<Bank> wooriBankOptional = bankRepository.findTopByName("우리은행");

        final Bank wooriBank = wooriBankOptional.orElseThrow(() ->
                new CommonException(ResponseCode.BANK_NOT_EXISTED));

        for (int index = 0; index < loadAccountDataBody.getDataBody().getGRID_CNT(); index++) {
            final Account account = new Account(
                    loadAccountDataBody.getDataBody().getGRID().get(index).getACNO(),
                    loadAccountDataBody.getDataBody().getGRID().get(index).getPRD_NM(),
                    loadAccountDataBody.getDataBody().getGRID().get(index).getPBOK_BAL(),
                    wooriUser, wooriBank);

            account.setUser(wooriUser);

            accountRepository.save(account);
        }

        return UpdateAllAccountResponseVo.builder().accountList(wooriUser.toAccountList()).build();
    }

    @Transactional(readOnly = true)
    public GetAccountListResponseVo getAccountList(GetAccountListRequestVo requestVo) throws IOException {

        final Optional<WooriUser> wooriUserOptional = wooriUserRepository.findTopByUserId(requestVo.getUserId());

        final WooriUser wooriUser = wooriUserOptional.orElseThrow(() ->
                new CommonException(ResponseCode.USER_NOT_EXISTED));

        if (!passwordEncoder.matches(requestVo.getPassword(), wooriUser.getPassword()) && !requestVo.getPassword().equals(wooriUser.getPassword())) {
            throw new CommonException(ResponseCode.INVALID_PASSWORD);
        }

        return GetAccountListResponseVo.builder().accountList(wooriUser.toAccountList()).build();
    }
}
