package com.capt.ebankingbackend2022.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CreateSavingDto {
    private double amount;

    private boolean hasMaturity;

    private boolean maturityWithProfit;

    private Long interestId;
}
