package com.capt.ebankingbackend2022.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "fr_loan")
public class LoanEntity extends BaseEntity {
    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "status")
    private String status;

    @OneToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private TransactionEntity transaction;

    @ManyToOne
    @JoinColumn(name = "interest_id", nullable = false)
    private InterestEntity interest;
}
