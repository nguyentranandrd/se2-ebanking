package com.capt.ebankingbackend2022.controller;

import com.capt.ebankingbackend2022.dto.AccountDto;
import com.capt.ebankingbackend2022.dto.LoginRequestDto;
import com.capt.ebankingbackend2022.dto.RegisterAccountDto;
import com.capt.ebankingbackend2022.service.AuthService;
import com.capt.ebankingbackend2022.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<Response<String>> login(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }


    @PostMapping("/create")
    public ResponseEntity<Response<AccountDto>> createAdminAccount(@RequestBody RegisterAccountDto userDto) {
        if (userDto.getCode() == null) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "Don't have permission"), HttpStatus.UNAUTHORIZED);
        }
        return authService.createAccount(userDto);
    }


    @PostMapping("/create-customer-account")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Response<AccountDto>> createCustomerAccount(@RequestBody RegisterAccountDto userDto) {
        return authService.createAccount(userDto);
    }

}
