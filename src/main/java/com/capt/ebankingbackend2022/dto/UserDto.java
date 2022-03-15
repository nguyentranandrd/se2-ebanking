package com.capt.ebankingbackend2022.dto;

import com.capt.ebankingbackend2022.entity.AccountEntity;
import com.capt.ebankingbackend2022.entity.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Getter
@Setter
public class UserDto extends BaseDto {
    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String address;

    private String citizenIdentification;

    private AccountEntity account;
}
