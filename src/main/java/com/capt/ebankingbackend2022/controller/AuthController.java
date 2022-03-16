package com.capt.ebankingbackend2022.controller;

import com.capt.ebankingbackend2022.dto.AccountDto;
import com.capt.ebankingbackend2022.dto.AccountInfoDto;
import com.capt.ebankingbackend2022.dto.AccountLoginDto;
import com.capt.ebankingbackend2022.dto.Response;
import com.capt.ebankingbackend2022.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;


    @GetMapping("/ping")
    public Response<String> pingServer() {
        return new Response<>(0, "ping success");
    }

    @PostMapping("/login")
    public ResponseEntity<Response<String>> login(@RequestBody AccountLoginDto accountLoginDto) {
        return authService.login(accountLoginDto);
    }


    @PostMapping("/create")
    public ResponseEntity<Response<AccountInfoDto>> createAdminAccount(@RequestBody AccountDto userDto) {
        if (userDto.getCode() == null) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "Don't have permission"), HttpStatus.UNAUTHORIZED);
        }
        return authService.createAccount(userDto);
    }


    @PostMapping("/create-customer-account")
    public ResponseEntity<Response<AccountInfoDto>> createCustomerAccount(@RequestBody AccountDto userDto) {
        return authService.createAccount(userDto);
    }

}
