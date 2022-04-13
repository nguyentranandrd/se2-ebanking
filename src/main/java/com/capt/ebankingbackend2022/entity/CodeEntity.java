package com.capt.ebankingbackend2022.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "fr_code")
public class CodeEntity extends BaseEntity {
    @Column(name = "code_create", nullable = false)
    private String code;

    @Column(name = "active")
    private boolean active;
}
