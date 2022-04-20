package com.capt.ebankingbackend2022.repository;

import com.capt.ebankingbackend2022.entity.LoanEntity;
import com.capt.ebankingbackend2022.entity.SavingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingRepository extends JpaRepository<SavingEntity, Long> {
}
