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
@Table(name = "FR_ROLE")
public class RoleEntity extends BaseEntity {
    @Column(name = "name", unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<AccountEntity> accounts;

    @Override
    public String toString() {
        return "RoleEntity{" +
                "name='" + name + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
