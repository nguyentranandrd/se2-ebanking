package com.capt.ebankingbackend2022.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class TransferAccountDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String email;
}
