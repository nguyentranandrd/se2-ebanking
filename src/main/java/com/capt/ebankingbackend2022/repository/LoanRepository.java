package com.capt.ebankingbackend2022.repository;

import com.capt.ebankingbackend2022.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
}
