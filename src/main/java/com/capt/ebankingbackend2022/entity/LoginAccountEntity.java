package com.capt.ebankingbackend2022.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "FR_LOGIN")
public class LoginAccountEntity extends BaseEntity {

    @Column(name = "phone_no", unique = true)
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "loginAccount", cascade = CascadeType.ALL)
    private UserEntity user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "login_role", joinColumns = @JoinColumn(name = "login_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roles;


    @Override
    public String toString() {
        return "AccountEntity{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", user=" + user +
                '}';
    }
}
