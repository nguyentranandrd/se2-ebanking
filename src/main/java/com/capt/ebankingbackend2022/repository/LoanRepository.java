package com.capt.ebankingbackend2022.repository;

import com.capt.ebankingbackend2022.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
    List<LoanEntity> findByStatusAndEndTimeLessThanEqual(String status, Date date);
}
