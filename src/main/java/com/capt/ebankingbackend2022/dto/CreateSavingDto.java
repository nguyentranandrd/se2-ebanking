package com.capt.ebankingbackend2022.dto;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSavingDto {
    private double amount;

    private boolean hasMaturity;

    private boolean maturityWithProfit;

    private Long interestId;


}
