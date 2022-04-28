package com.capt.ebankingbackend2022.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Getter
@Setter
@Data
@Table(name = "fr_interest")
public class InterestEntity extends BaseEntity {
    @Column(name = "rate")
    private double rate;
    @Column(name = "instantRate")
    private double instantRate;
    @Column(name = "duration")
    private long duration;
    @Column
    private String type; //(saving/loan)

    @OneToMany(mappedBy = "interest")
    private Set<LoanEntity> loans;

    @OneToMany(mappedBy = "interest")
    private Set<SavingEntity> savings;
}
