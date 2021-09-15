package com.wooribank.backend.service;

import com.wooribank.backend.domain.Account;
import com.wooribank.backend.domain.Bank;
import com.wooribank.backend.domain.WooriUser;
import com.wooribank.backend.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataCreateService {
    private final WooriUserRepository wooriUserRepository;
    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;
    private final TransactionRepository transactionRepository;
    private final ReturnRequestRepository returnRequestRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Void createData() {
        WooriUser wooriUser1 = new WooriUser(
                "kimwoori123", passwordEncoder.encode("kimwoori123"),
                "김우리", "+821012345678");
        WooriUser wooriUser2 = new WooriUser(
                "kimthief123", passwordEncoder.encode("kimthief123"),
                "김도둑", "+821012345679");

        wooriUserRepository.save(wooriUser1);
        wooriUserRepository.save(wooriUser2);

        Bank bank1 = new Bank("우리은행");
        Bank bank2 = new Bank("국민은행");

        bankRepository.save(bank1);
        bankRepository.save(bank2);

        Account account1 = new Account("1002-374-912345", "WON 통장", 3000000, wooriUser1, bank1);
        Account account2 = new Account("1002-456-812345", "스무살우리 통장", 650000, wooriUser1, bank1);
        Account account3 = new Account("1002-313-612345", "위비모바일 통장", 54350000, wooriUser1, bank1);
        Account account4 = new Account("1040-540-812345", "우리 Magic6 적금", 41000000, wooriUser1, bank1);
        Account account5 = new Account("1073-048-412345", "주택청약종합저축", 7700000, wooriUser1, bank1);

        Account account6 = new Account("1002-374-967890", "WON 통장", 4200000, wooriUser2, bank1);
        Account account7 = new Account("1002-456-867890", "스무살우리 통장", 350000, wooriUser2, bank1);
        Account account8 = new Account("1002-313-667890", "위비모바일 통장", 672130000, wooriUser2, bank1);
        Account account9 = new Account("1040-540-867890", "우리 Magic6 적금", 13000000, wooriUser2, bank1);
        Account account10 = new Account("1073-048-467890", "주택청약종합저축", 4900000, wooriUser2, bank1);

        accountRepository.save(account1);
        accountRepository.save(account2);
        accountRepository.save(account3);
        accountRepository.save(account4);
        accountRepository.save(account5);
        accountRepository.save(account6);
        accountRepository.save(account7);
        accountRepository.save(account8);
        accountRepository.save(account9);
        accountRepository.save(account10);

        return null;
    }


}
