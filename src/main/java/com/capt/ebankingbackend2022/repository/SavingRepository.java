package com.capt.ebankingbackend2022.repository;

import com.capt.ebankingbackend2022.entity.SavingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface SavingRepository extends JpaRepository<SavingEntity, Long> {
    List<SavingEntity> findByStatusAndEndTimeLessThanEqual(String status, Date date);
}
