package com.wooribank.backend.repository;

import com.wooribank.backend.domain.WooriUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WooriUserRepository extends JpaRepository<WooriUser, Long> {

    Optional<WooriUser> findTopByUserId(String userId);
}
