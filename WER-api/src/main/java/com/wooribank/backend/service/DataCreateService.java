package com.wooribank.backend.service;

import com.wooribank.backend.domain.Account;
import com.wooribank.backend.domain.Bank;
import com.wooribank.backend.domain.Transaction;
import com.wooribank.backend.domain.WooriUser;
import com.wooribank.backend.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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

        Transaction transaction1 = new Transaction(LocalDateTime.of(2021, 9, 15, 15, 47), "김우리", "김도둑", "FBS출금", "FBS입금", 150000, account1, account6);
        Transaction transaction2 = new Transaction(LocalDateTime.of(2021, 9, 11, 18, 11), "김도둑", "김우리", "FBS출금", "FBS입금", 200000, account6, account2);
        Transaction transaction3 = new Transaction(LocalDateTime.of(2021, 8, 10, 8, 32), "김우리", "김도둑", "FBS출금", "FBS입금", 3000, account1, account6);
        Transaction transaction4 = new Transaction(LocalDateTime.of(2021, 8, 14, 10, 19), "김도둑", "김우리", "FBS출금", "FBS입금", 2000, account6, account1);
        Transaction transaction5 = new Transaction(LocalDateTime.of(2021, 9, 1, 4, 41), "김우리", "김도둑", "FBS출금", "FBS입금", 33000, account1, account6);
        Transaction transaction6 = new Transaction(LocalDateTime.of(2021, 9, 4, 7, 10), "김도둑", "김우리", "FBS출금", "FBS입금", 91000, account6, account1);
        Transaction transaction7 = new Transaction(LocalDateTime.of(2021, 9, 15, 15, 47), "김우리", "김도둑", "FBS출금", "FBS입금", 150000, account2, account7);
        Transaction transaction8 = new Transaction(LocalDateTime.of(2021, 9, 24, 18, 10), "김도둑", "김우리", "FBS출금", "FBS입금", 394000, account7, account2);
        Transaction transaction9 = new Transaction(LocalDateTime.of(2021, 8, 17, 8, 32), "김우리", "김도둑", "FBS출금", "FBS입금", 3000, account2, account7);
        Transaction transaction10 = new Transaction(LocalDateTime.of(2021, 8, 25, 10, 19), "김도둑", "김우리", "FBS출금", "FBS입금", 2000, account7, account2);
        Transaction transaction11 = new Transaction(LocalDateTime.of(2021, 7, 3, 4, 41), "김우리", "김도둑", "FBS출금", "FBS입금", 33000, account2, account7);
        Transaction transaction12 = new Transaction(LocalDateTime.of(2021, 7, 6, 7, 10), "김도둑", "김우리", "FBS출금", "FBS입금", 91000, account7, account2);

        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);
        transactionRepository.save(transaction3);
        transactionRepository.save(transaction4);
        transactionRepository.save(transaction5);
        transactionRepository.save(transaction6);
        transactionRepository.save(transaction7);
        transactionRepository.save(transaction8);
        transactionRepository.save(transaction9);
        transactionRepository.save(transaction10);
        transactionRepository.save(transaction11);
        transactionRepository.save(transaction12);

        return null;
    }


}
