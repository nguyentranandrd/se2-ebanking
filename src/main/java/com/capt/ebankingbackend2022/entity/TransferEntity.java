package com.capt.ebankingbackend2022.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "fr_transfer")
public class TransferEntity extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "from_account_id", referencedColumnName = "id")
    private AccountEntity fromAccount;

    @OneToOne
    @JoinColumn(name = "to_account_id", referencedColumnName = "id")
    private AccountEntity toAccount;

    @OneToOne
    @JoinColumn(name = "from_transaction_id", referencedColumnName = "id")
    private TransactionEntity fromTransaction;

    @OneToOne
    @JoinColumn(name = "to_transaction_id", referencedColumnName = "id")
    private TransactionEntity toTransaction;
}
