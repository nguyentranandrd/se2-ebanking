package com.capt.ebankingbackend2022.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "fr_saving")
public class SavingEntity extends BaseEntity {
    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "has_maturity")
    private boolean hasMaturity;

    @Column(name = "maturity_with_profit")
    private boolean maturityWithProfit;

    @Column(name = "status")
    private String status;

    @OneToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private TransactionEntity transaction;

    @ManyToOne
    @JoinColumn(name = "interest_id", nullable = false)
    private InterestEntity interest;


}
