package com.capt.ebankingbackend2022.service;


import com.capt.ebankingbackend2022.dto.AccountDto;
import com.capt.ebankingbackend2022.dto.AccountLoginDto;
import com.capt.ebankingbackend2022.dto.Response;

public interface AuthService {

    Response<String> login(AccountLoginDto accountLoginDto);

    Response<AccountDto> saveUser(AccountDto userDto);
}
