package com.wooribank.backend.repository;

import com.wooribank.backend.domain.Account;
import com.wooribank.backend.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllBySentAccountAndTimeOfOccurrenceBetweenOrderByTimeOfOccurrenceAsc(
            Account sentAccount, LocalDateTime startTimeOfOccurrence, LocalDateTime endTimeOfOccurrence);

    List<Transaction> findAllByReceivedAccountAndTimeOfOccurrenceBetweenOrderByTimeOfOccurrenceAsc(
            Account receivedAccount, LocalDateTime startTimeOfOccurrence, LocalDateTime endTimeOfOccurrence);
}
