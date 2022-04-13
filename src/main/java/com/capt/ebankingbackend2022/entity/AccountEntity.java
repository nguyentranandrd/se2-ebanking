package com.capt.ebankingbackend2022.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "FR_ACCOUNT")
public class AccountEntity extends BaseEntity {

    @Column(name = "phone_no", unique = true)
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private double balance;

    @OneToOne(mappedBy = "account")
    private UserEntity user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_role", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roles;

    @OneToMany(mappedBy = "owner")
    private List<TransactionEntity> transactions;

    @Override
    public String toString() {
        return "AccountEntity{" + "phoneNumber='" + phoneNumber + '\'' + ", password='" + password + '\'' + ", user=" + user + '}';
    }
}
