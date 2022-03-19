package com.capt.ebankingbackend2022.service.impl;

import com.capt.ebankingbackend2022.dto.*;
import com.capt.ebankingbackend2022.entity.LoginAccountEntity;
import com.capt.ebankingbackend2022.entity.CodeEntity;
import com.capt.ebankingbackend2022.entity.RoleEntity;
import com.capt.ebankingbackend2022.repository.AccountRepository;
import com.capt.ebankingbackend2022.repository.CodeRepository;
import com.capt.ebankingbackend2022.repository.RoleRepository;
import com.capt.ebankingbackend2022.security.JwtTokenProvider;
import com.capt.ebankingbackend2022.service.AuthService;
import com.capt.ebankingbackend2022.utils.Authority;
import com.capt.ebankingbackend2022.utils.RegexValidationUtil;
import com.capt.ebankingbackend2022.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthServiceImpl extends BaseServiceImpl implements AuthService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CodeRepository codeRepository;

    @Autowired
    RoleRepository roleRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public ResponseEntity<Response<String>> login(LoginRequestDto loginRequestDto) {
        Response<String> response;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getPhoneNumber(), loginRequestDto.getPassword()));
            String token = jwtTokenProvider.createToken(loginRequestDto.getPhoneNumber(), accountRepository.findByPhoneNumber(loginRequestDto.getPhoneNumber()).getRoles());
            response = new Response<>(0, "login success", token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response = new Response<>(1, "phone number or password not true");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<Response<LoginAccountDto>> createLoginAccount(RegisterAccountDto userDto) {
        userDto.setCreatedAt(new Date());
        LoginAccountEntity userEntity = modelMapper.map(userDto, LoginAccountEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        String code = userDto.getCode();
        String roleString = Authority.USER;
        CodeEntity codeEntity = null;
        if (code != null) {
            codeEntity = codeRepository.findByCode(code);
            if (codeEntity == null) {
                return new ResponseEntity<>(new Response<>(1, "Admin code not existed"), HttpStatus.BAD_REQUEST);
            }
            if (!codeEntity.isActive()) {
                return new ResponseEntity<>(new Response<>(1, "Admin code is inactive"), HttpStatus.BAD_REQUEST);
            }
            roleString = Authority.ADMIN;
        }
        RoleEntity role = roleRepository.findByName(roleString);
        if (role == null) {
            role = new RoleEntity();
            role.setName(roleString);
            role.setCreatedAt(new Date());
            roleRepository.save(role);
        }
        userEntity.setRoles(Collections.singletonList(role));
        if (accountRepository.existsByPhoneNumber(userDto.getPhoneNumber()))
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "phone number has been used"), HttpStatus.BAD_REQUEST);
        if (RegexValidationUtil.is10NumberPhone(userDto.getPhoneNumber())) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "Invalid phone number (ex: 0123456789 or +840123456789)"), HttpStatus.BAD_REQUEST);
        }
        if (roleString.equals(Authority.ADMIN)) {
            codeEntity.setActive(false);
            codeEntity.setUpdatedAt(new Date());
            codeRepository.save(codeEntity);
        }
        LoginAccountEntity savedAccount = accountRepository.save(userEntity);
        LoginAccountDto loginAccountDto = modelMapper.map(savedAccount, LoginAccountDto.class);
        List<RoleDto> roleDtos = new ArrayList<>();
        for (RoleEntity r :
                savedAccount.getRoles()) {
            roleDtos.add(modelMapper.map(r, RoleDto.class));
        }
        loginAccountDto.setRoles(roleDtos);
        return new ResponseEntity<>(
                new Response<>(0, "create account success", loginAccountDto),
                HttpStatus.CREATED
        );
    }


}
