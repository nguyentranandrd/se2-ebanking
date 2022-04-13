package com.capt.ebankingbackend2022.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "fr_transaction")
public class TransactionEntity extends BaseEntity {
    @Column(name = "balance_before")
    private double balanceBefore;
    @Column(name = "balance_after")
    private double balanceAfter;
    @Column(name = "transaction_type")
    private String transactionType;
    @OneToOne(mappedBy = "transaction")
    private TranferEntity tranfer;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private AccountEntity owner;


}
