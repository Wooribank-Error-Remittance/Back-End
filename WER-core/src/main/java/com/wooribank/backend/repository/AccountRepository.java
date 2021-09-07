package com.wooribank.backend.repository;

import com.wooribank.backend.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByNumber(String number);
}
