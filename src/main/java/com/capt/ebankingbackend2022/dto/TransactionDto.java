package com.capt.ebankingbackend2022.dto;

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
}
