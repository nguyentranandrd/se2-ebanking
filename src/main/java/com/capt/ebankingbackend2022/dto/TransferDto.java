package com.capt.ebankingbackend2022.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TransferDto extends BaseDto {
    private TransferAccountDto fromAccount;

    private TransferAccountDto toAccount;
}
