package com.capt.ebankingbackend2022.service;

import com.capt.ebankingbackend2022.dto.AccountDto;
import com.capt.ebankingbackend2022.dto.UserDto;
import com.capt.ebankingbackend2022.utils.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    ResponseEntity<Response<AccountDto>> updateUserInfo(Long accountId, UserDto userDto);

    ResponseEntity<Response<AccountDto>> getLoggedUserInfo();

    ResponseEntity<Response<AccountDto>> getUserByAccountId(Long id);

    ResponseEntity<Response<Page<AccountDto>>> getPageableUsers(Pageable pageable);

    ResponseEntity<Response<Boolean>> deleteAccount(Long id);

    ResponseEntity<Response<Boolean>> changePassword(String previousPass, String newPassword);

    void updateAvatar(String path);
}
