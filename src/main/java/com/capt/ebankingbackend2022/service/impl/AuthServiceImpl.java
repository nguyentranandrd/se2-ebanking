package com.capt.ebankingbackend2022.service.impl;

import com.capt.ebankingbackend2022.dto.AccountDto;
import com.capt.ebankingbackend2022.dto.AccountLoginDto;
import com.capt.ebankingbackend2022.dto.Response;
import com.capt.ebankingbackend2022.entity.AccountEntity;
import com.capt.ebankingbackend2022.entity.CodeEntity;
import com.capt.ebankingbackend2022.entity.RoleEntity;
import com.capt.ebankingbackend2022.repository.AccountRepository;
import com.capt.ebankingbackend2022.repository.RoleRepository;
import com.capt.ebankingbackend2022.security.JwtTokenProvider;
import com.capt.ebankingbackend2022.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

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
    public Response<String> login(AccountLoginDto accountLoginDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountLoginDto.getPhoneNumber(), accountLoginDto.getPassword()));
            String token = jwtTokenProvider.createToken(accountLoginDto.getPhoneNumber(), accountRepository.findByPhoneNumber(accountLoginDto.getPhoneNumber()).getRoles());
            return new Response<>(0, "login success", token);
        } catch (Exception e) {
            return new Response<>(1, "phone number or password not true");
        }
    }

    @Override
    public Response<AccountDto> saveUser(AccountDto userDto) {
        userDto.setCreatedAt(new Date());
        AccountEntity userEntity = modelMapper.map(userDto, AccountEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        String code = userDto.getCode();
        String roleString = "user";
        CodeEntity codeEntity = null;
        if (code != null) {
            codeEntity = codeRepository.findByCode(code);
            if (codeEntity == null) {
                return new Response<>(1, "Admin code not existed");
            }
            if (!codeEntity.isActive()) {
                return new Response<>(1, "Admin code is inactive");
            }
            roleString = "admin";
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
            return new Response<>(1, "phone number has been used");
        if (roleString.equals("admin")) {
            codeEntity.setActive(false);
            codeRepository.save(codeEntity);
        }
        return new Response<>(0, "create account success", modelMapper.map(accountRepository.save(userEntity), AccountDto.class));
    }


}
