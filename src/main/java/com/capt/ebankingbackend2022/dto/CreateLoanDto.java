package com.capt.ebankingbackend2022.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CreateLoanDto {
    private double amount;

    private Long interestId;
}
