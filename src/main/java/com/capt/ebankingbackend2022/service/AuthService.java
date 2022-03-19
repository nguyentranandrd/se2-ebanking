package com.capt.ebankingbackend2022.service;


import com.capt.ebankingbackend2022.dto.RegisterAccountDto;
import com.capt.ebankingbackend2022.dto.AccountDto;
import com.capt.ebankingbackend2022.dto.LoginRequestDto;
import com.capt.ebankingbackend2022.utils.Response;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<Response<String>> login(LoginRequestDto loginRequestDto);

    ResponseEntity<Response<AccountDto>> createAccount(RegisterAccountDto userDto);
}
