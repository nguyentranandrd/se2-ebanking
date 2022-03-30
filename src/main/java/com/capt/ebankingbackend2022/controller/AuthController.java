package com.capt.ebankingbackend2022.controller;

import com.capt.ebankingbackend2022.dto.AccountDto;
import com.capt.ebankingbackend2022.dto.LoginRequestDto;
import com.capt.ebankingbackend2022.dto.RegisterAccountDto;
import com.capt.ebankingbackend2022.entity.CodeEntity;
import com.capt.ebankingbackend2022.repository.CodeRepository;
import com.capt.ebankingbackend2022.service.AuthService;
import com.capt.ebankingbackend2022.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    CodeRepository codeRepository;


    @GetMapping("/ping")
    public Response<String> pingServer() {
        return new Response<>(0, "ping success");
    }


    @GetMapping("/code")
    public Response<CodeEntity> newAdminCode() {
        String code = UUID.randomUUID().toString();
        CodeEntity codeEntity = new CodeEntity();
        codeEntity.setCode(code);
        codeEntity.setActive(true);
        codeEntity.setCreatedAt(new Date());
        codeRepository.save(codeEntity);
        return new Response<>(0, "success", codeEntity);
    }


    @PostMapping("/login")
    public ResponseEntity<Response<String>> login(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }

    @PostMapping("/create")
    public ResponseEntity<Response<AccountDto>> createAdminLoginAccount(@RequestBody RegisterAccountDto userDto) {
        if (userDto.getCode() == null) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "Don't have permission"), HttpStatus.UNAUTHORIZED);
        }
        return authService.createLoginAccount(userDto);
    }


    @PostMapping("/create-customer-account")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Response<AccountDto>> createCustomerLoginAccount(@RequestBody RegisterAccountDto userDto) {
        return authService.createLoginAccount(userDto);
    }


}
