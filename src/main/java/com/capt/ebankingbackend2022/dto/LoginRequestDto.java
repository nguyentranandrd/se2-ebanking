package com.capt.ebankingbackend2022.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
public class LoginRequestDto extends BaseDto {
    private String phoneNumber;
    private String password;
}
