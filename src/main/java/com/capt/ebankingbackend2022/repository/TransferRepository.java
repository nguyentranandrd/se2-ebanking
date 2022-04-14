package com.capt.ebankingbackend2022.repository;

import com.capt.ebankingbackend2022.entity.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<TransferEntity, Long> {
}
