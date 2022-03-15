package com.capt.ebankingbackend2022.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "create_at", nullable = false, updatable = false)
    protected Date createdAt;

    @Column(name = "update_at")
    protected Date updatedAt;
}
