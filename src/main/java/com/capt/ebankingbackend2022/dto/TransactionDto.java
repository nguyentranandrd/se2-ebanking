package com.capt.ebankingbackend2022.dto;

import com.capt.ebankingbackend2022.entity.AccountEntity;
import com.capt.ebankingbackend2022.entity.LoanEntity;
import com.capt.ebankingbackend2022.entity.SavingEntity;
import com.capt.ebankingbackend2022.entity.TransferEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
public class TransactionDto extends BaseDto {

    private double balanceBefore;

    private double balanceAfter;

    private String transactionType;

    private double amount;

    private TransferDto fromTransfer;

    private TransferDto toTransfer;

    private SavingDto saving;

    private LoanDto loan;

    private AccountDto owner;
}
