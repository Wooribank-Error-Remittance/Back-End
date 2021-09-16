package com.wooribank.backend.repository;

import com.wooribank.backend.domain.Bank;
import com.wooribank.backend.domain.WooriUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Long> {

    Optional<Bank> findTopByName(String name);
}
