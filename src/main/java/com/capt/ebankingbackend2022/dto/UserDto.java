package com.capt.ebankingbackend2022.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
public class UserDto extends BaseDto {
    private String firstName;

    private String lastName;

    private String avatar;

    private String email;

    private String address;

    private String citizenIdentification;

    private AccountDto account;

}
