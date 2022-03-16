package com.capt.ebankingbackend2022.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "FR_ROLE")
public class RoleEntity extends BaseEntity {
    @Column(name = "name", unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<AccountEntity> accounts;

    @Override
    public String toString() {
        return "RoleEntity{" +
                "name='" + name + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
