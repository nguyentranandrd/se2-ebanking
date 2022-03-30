package com.capt.ebankingbackend2022.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "FR_USER")
public class UserEntity {
    @Id
    @Column(name = "id")
    protected Long id;

    @Column(name = "create_at", nullable = false, updatable = false)
    protected Date createdAt;

    @Column(name = "update_at")
    protected Date updatedAt;


    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "citizen_id", nullable = false, unique = true)
    private String citizenIdentification;

    @Column(name = "avatar", length = 1024)
    private String avatar;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountEntity account;

}
