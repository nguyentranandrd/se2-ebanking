package com.capt.ebankingbackend2022.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
public class AccountDto extends BaseDto {
    private String phoneNumber;

    private List<RoleDto> roles;
}
