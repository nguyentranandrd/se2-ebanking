package com.capt.ebankingbackend2022.service;


import com.capt.ebankingbackend2022.dto.AccountDto;
import com.capt.ebankingbackend2022.dto.AccountInfoDto;
import com.capt.ebankingbackend2022.dto.AccountLoginDto;
import com.capt.ebankingbackend2022.dto.Response;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<Response<String>> login(AccountLoginDto accountLoginDto);

    ResponseEntity<Response<AccountInfoDto>> createAccount(AccountDto userDto);
}
