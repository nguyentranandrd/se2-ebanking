package com.capt.ebankingbackend2022.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "FR_USER")
public class UserEntity extends BaseEntity {
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
    @JoinColumn(name = "login_id", referencedColumnName = "id")
    private LoginAccountEntity loginAccount;

}
