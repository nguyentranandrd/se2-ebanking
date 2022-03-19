package com.capt.ebankingbackend2022.repository;

import com.capt.ebankingbackend2022.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByCitizenIdentification(String id);
    boolean existsByEmail(String email);
}
