package com.capt.ebankingbackend2022.repository;

import com.capt.ebankingbackend2022.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    AccountEntity findByPhoneNumber(String phone);

    AccountEntity findByUser_Email(String email);

    boolean existsByPhoneNumber(String phone);
}
