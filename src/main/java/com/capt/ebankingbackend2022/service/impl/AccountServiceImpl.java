package com.capt.ebankingbackend2022.service.impl;

import com.capt.ebankingbackend2022.dto.AccountDto;
import com.capt.ebankingbackend2022.dto.DestinationUserDto;
import com.capt.ebankingbackend2022.dto.UserDto;
import com.capt.ebankingbackend2022.entity.AccountEntity;
import com.capt.ebankingbackend2022.entity.UserEntity;
import com.capt.ebankingbackend2022.mapper.AccountMapper;
import com.capt.ebankingbackend2022.repository.AccountRepository;
import com.capt.ebankingbackend2022.repository.UserRepository;
import com.capt.ebankingbackend2022.service.AccountService;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountServiceImpl extends BaseServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        userEntity.setEmail(userDto.getEmail());
        userEntity.setLastName(userDto.getLastName());
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

    @Override
    public ResponseEntity<Response<Boolean>> deleteAccount(Long id) {
        if (!accountRepository.existsById(id))
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "account not found", false), HttpStatus.BAD_REQUEST);
        if (userRepository.existsById(id))
            userRepository.deleteById(id);
        accountRepository.deleteById(id);
        return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, "delete success", true), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response<Boolean>> changePassword(String previousPass, String newPassword) {
        if (!RegexValidationUtil.isValidPassword(newPassword)){
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "password is not strong enough.", false), HttpStatus.BAD_REQUEST);
        }
        AccountEntity account = getLoggedAccount();
        if (!passwordEncoder.matches(previousPass, account.getPassword())) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "password is not true", false), HttpStatus.BAD_REQUEST);
        }
        account.setPassword(passwordEncoder.encode(newPassword));
        account.setUpdatedAt(new Date());
        accountRepository.save(account);
        return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, "change password success", true), HttpStatus.OK);
    }

    @Override
    public void updateAvatar(String url) {
        AccountEntity account = getLoggedAccount();
        account.getUser().setAvatar(url);
        account.getUser().setUpdatedAt(new Date());
        accountRepository.save(account);
    }

    @Override
    public ResponseEntity<Response<Boolean>> updateAccountBalance(Long id, double balance) {
        AccountEntity accountEntity = accountRepository.findById(id).orElse(null);
        if (accountEntity == null)
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "Account not found", false), HttpStatus.BAD_REQUEST);
        if (balance < 0)
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "Balance must be greater than 0", false), HttpStatus.BAD_REQUEST);
        accountEntity.setBalance(balance);
        accountRepository.save(accountEntity);
        return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, "Update balance success. Current balance is " + balance, true), HttpStatus.OK);
    }

    @Override
    public String getUserAvatar(Long id) {
        UserEntity user = userRepository.findById(id).orElse(null);
        if (user == null) return null;
        return user.getAvatar();
    }

    @Override
    public ResponseEntity<Response<DestinationUserDto>> getDestinationUser(String type, String value) {
        AccountEntity account = null;
        if (type.equals("phone")) {
            account = accountRepository.findByPhoneNumber(value);
        } else if (type.equals("email"))
            account = accountRepository.findByUser_Email(value);
        if (account == null)
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "User not found"), HttpStatus.BAD_REQUEST);
        DestinationUserDto user = modelMapper.map(account.getUser(), DestinationUserDto.class);
        return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, "Success", user), HttpStatus.OK);
    }

    private AccountEntity getLoggedAccount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return accountRepository.findByPhoneNumber(userDetails.getUsername());
    }
}
