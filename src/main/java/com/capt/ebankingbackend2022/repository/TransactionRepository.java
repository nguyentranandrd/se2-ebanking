package com.capt.ebankingbackend2022.repository;

import com.capt.ebankingbackend2022.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    Page<TransactionEntity> getByOwnerId(long ownerId, Pageable pageable);

    Page<TransactionEntity> getByOwnerIdAndTransactionType(long ownerId, String transactionType, Pageable pageable);

}
