package com.capt.ebankingbackend2022.service.impl;

import com.capt.ebankingbackend2022.entity.CodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepository extends JpaRepository<CodeEntity, Long> {
    CodeEntity findByCode(String code);
}
