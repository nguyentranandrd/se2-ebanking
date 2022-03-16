package com.capt.ebankingbackend2022.dto;

import com.capt.ebankingbackend2022.entity.RoleEntity;
import com.capt.ebankingbackend2022.entity.UserEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class AccountDto extends BaseDto {
    private String phoneNumber;

    private String password;

    private String code;

}
