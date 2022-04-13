package com.capt.ebankingbackend2022.repository;

import com.capt.ebankingbackend2022.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
