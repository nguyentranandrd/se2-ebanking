package com.capt.ebankingbackend2022.controller;

import com.capt.ebankingbackend2022.dto.AccountDto;
import com.capt.ebankingbackend2022.dto.AccountLoginDto;
import com.capt.ebankingbackend2022.dto.Response;
import com.capt.ebankingbackend2022.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/ping")
    public boolean pingServer() {
        return true;
    }

    @PostMapping("/login")
    public Response<String> login(@RequestBody AccountLoginDto accountLoginDto) {
        return authService.login(accountLoginDto);
    }

    @PostMapping("/encode")
    public Response<String> createPassword(@RequestPart("pass") String pass) {
        return new Response<>(Response.STATUS_SUCCESS, "success", encoder.encode(pass));
    }
    @PostMapping("/create")
    Response<AccountDto> register(@RequestBody AccountDto userDto) {
        return authService.saveUser(userDto);
    }



//    public Response<AccountDto> createAccount(AccountDto accountDto) {
//
//    }
}
