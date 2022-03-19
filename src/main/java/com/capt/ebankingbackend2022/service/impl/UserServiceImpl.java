package com.capt.ebankingbackend2022.service.impl;

import com.capt.ebankingbackend2022.dto.AccountDto;
import com.capt.ebankingbackend2022.dto.UserDto;
import com.capt.ebankingbackend2022.entity.AccountEntity;
import com.capt.ebankingbackend2022.entity.UserEntity;
import com.capt.ebankingbackend2022.mapper.UserMapper;
import com.capt.ebankingbackend2022.repository.AccountRepository;
import com.capt.ebankingbackend2022.repository.UserRepository;
import com.capt.ebankingbackend2022.security.MyUserDetails;
import com.capt.ebankingbackend2022.service.UserService;
import com.capt.ebankingbackend2022.utils.RegexValidationUtil;
import com.capt.ebankingbackend2022.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseEntity<Response<UserDto>> updateUserInfo(Long accountId, UserDto userDto) {
        AccountEntity account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "Account not found"), HttpStatus.BAD_REQUEST);
        }

        if (!RegexValidationUtil.isEmail(userDto.getEmail())) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "Email is invalid"), HttpStatus.BAD_REQUEST);
        }

        if (account.getUser() == null) {
            return createUser(account, userDto);
        }
        return updateUser(account, userDto);
    }

    private ResponseEntity<Response<UserDto>> updateUser(AccountEntity account, UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail()) && !account.getUser().getEmail().equals(userDto.getEmail())) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "Email is existed"), HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = account.getUser();
        userEntity.setUpdatedAt(new Date());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setAvatar(userDto.getAvatar());
        userEntity.setAddress(userDto.getAddress());
        accountRepository.save(account);
        userDto = userMapper.toDto(userEntity);
        return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, "Update user info successfully", userDto), HttpStatus.OK);
    }

    private ResponseEntity<Response<UserDto>> createUser(AccountEntity account, UserDto userDto) {
        if (userRepository.existsByCitizenIdentification(userDto.getCitizenIdentification())) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "Citizen identification is existed"), HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "Email is existed"), HttpStatus.BAD_REQUEST);
        }
        userDto.setCreatedAt(new Date());
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setAccount(account);
        userEntity = userRepository.save(userEntity);
        AccountDto accountDto = modelMapper.map(account, AccountDto.class);
        UserDto userBodyResponseDto = modelMapper.map(userEntity, UserDto.class);
        userBodyResponseDto.setAccount(accountDto);
        return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, "Save user info successfully", userBodyResponseDto), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<UserDto> getLoggedUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        AccountEntity account = accountRepository.findByPhoneNumber(userDetails.getUsername());
        UserEntity userEntity = account.getUser();
        if (userEntity == null) {
            userEntity = new UserEntity();
            userEntity.setAccount(account);
        }
        UserDto userDto = userMapper.toDto(userEntity);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
