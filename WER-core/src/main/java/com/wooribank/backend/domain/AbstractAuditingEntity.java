package com.wooribank.backend.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractAuditingEntity {

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "timestamp")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "timestamp")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
