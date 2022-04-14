package com.capt.ebankingbackend2022.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TransferRequestDto {
    private double amount;
    private long toAccount;
}
