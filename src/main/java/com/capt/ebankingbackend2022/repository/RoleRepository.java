package com.capt.ebankingbackend2022.repository;

import com.capt.ebankingbackend2022.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
}
