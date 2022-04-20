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

    @Column(name = "amount")
    private double amount;

    @Column(name = "transaction_type")
    private String transactionType;

    @OneToOne(mappedBy = "fromTransaction")
    private TransferEntity fromTransfer;

    @OneToOne(mappedBy = "toTransaction")
    private TransferEntity toTransfer;

    @OneToOne(mappedBy = "transaction")
    private SavingEntity saving;

    @OneToOne(mappedBy = "transaction")
    private LoanEntity loan;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private AccountEntity owner;

}
