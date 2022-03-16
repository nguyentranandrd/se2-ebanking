package com.capt.ebankingbackend2022.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "FR_ACCOUNT")
public class AccountEntity extends BaseEntity {

    @Column(name = "phone_no", unique = true)
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "account")
    private UserEntity user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_role", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roles;

    @Override
    public String toString() {
        return "AccountEntity{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", user=" + user +
                ", roles=" + roles +
                '}';
    }
}
