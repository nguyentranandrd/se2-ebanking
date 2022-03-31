package com.capt.ebankingbackend2022.service.impl;

import com.capt.ebankingbackend2022.dto.AccountDto;
import com.capt.ebankingbackend2022.dto.UserDto;
import com.capt.ebankingbackend2022.entity.AccountEntity;
import com.capt.ebankingbackend2022.entity.UserEntity;
import com.capt.ebankingbackend2022.mapper.AccountMapper;
import com.capt.ebankingbackend2022.repository.AccountRepository;
import com.capt.ebankingbackend2022.repository.UserRepository;
import com.capt.ebankingbackend2022.service.UserService;
import com.capt.ebankingbackend2022.utils.RegexValidationUtil;
import com.capt.ebankingbackend2022.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public ResponseEntity<Response<AccountDto>> updateUserInfo(Long accountId, UserDto userDto) {
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

    private ResponseEntity<Response<AccountDto>> updateUser(AccountEntity account, UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail()) && !account.getUser().getEmail().equals(userDto.getEmail())) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "Email is existed"), HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = account.getUser();
        userEntity.setUpdatedAt(new Date());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setAvatar(userDto.getAvatar());
        userEntity.setAddress(userDto.getAddress());
        account = accountRepository.save(account);
        AccountDto accountDto = accountMapper.toDto(account);
        return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, "Update user info successfully", accountDto), HttpStatus.OK);
    }

    private ResponseEntity<Response<AccountDto>> createUser(AccountEntity account, UserDto userDto) {
        if (userRepository.existsByCitizenIdentification(userDto.getCitizenIdentification())) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "Citizen identification is existed"), HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "Email is existed"), HttpStatus.BAD_REQUEST);
        }
        userDto.setCreatedAt(new Date());
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setId(account.getId());
        userEntity.setAccount(account);
        userRepository.save(userEntity);
        AccountDto accountDto = accountMapper.toDto(account);
        return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, "Save user info successfully", accountDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response<AccountDto>> getLoggedUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        AccountEntity account = accountRepository.findByPhoneNumber(userDetails.getUsername());
        AccountDto accountDto = accountMapper.toDto(account);
        return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, accountDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response<AccountDto>> getUserByAccountId(Long id) {
        AccountEntity account = accountRepository.findById(id).orElse(null);
        if (account == null)
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, ""), HttpStatus.BAD_REQUEST);
        AccountDto accountDto = accountMapper.toDto(account);
        return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, accountDto), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Response<Page<AccountDto>>> getPageableUsers(Pageable pageable) {
        Page<AccountEntity> accountEntityPage;
        try {
            accountEntityPage = accountRepository.findAll(pageable);
        } catch (PropertyReferenceException exception) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "sort param not found"), HttpStatus.BAD_REQUEST);
        }
        Page<AccountDto> accountDtoPage = accountEntityPage.map(accountEntity -> accountMapper.toDto(accountEntity));
        return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, "success", accountDtoPage), HttpStatus.OK);
    }
}
