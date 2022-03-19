package com.capt.ebankingbackend2022.repository;

import com.capt.ebankingbackend2022.entity.LoginAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<LoginAccountEntity, Long> {
    LoginAccountEntity findByPhoneNumber(String phone);

    boolean existsByPhoneNumber(String phone);
}
