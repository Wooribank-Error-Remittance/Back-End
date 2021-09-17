package com.wooribank.backend.repository;

import com.wooribank.backend.domain.Bank;
import com.wooribank.backend.domain.ReturnRequest;
import com.wooribank.backend.domain.WooriUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReturnRequestRepository extends JpaRepository<ReturnRequest, Long> {

    List<ReturnRequest> findAllBySentUserOrderByCreatedAt(WooriUser sentUser);

    List<ReturnRequest> findAllByReceivedUserOrderByCreatedAt(WooriUser receivedUser);
}
