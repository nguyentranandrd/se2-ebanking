package com.capt.ebankingbackend2022.service;

import com.capt.ebankingbackend2022.dto.UserDto;
import com.capt.ebankingbackend2022.utils.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Response<UserDto>> updateUserInfo(Long accountId, UserDto userDto);

    ResponseEntity<Response<UserDto>> getLoggedUserInfo();

    ResponseEntity<Response<UserDto>> getUserByAccountId(Long id);

    ResponseEntity<Response<Page<UserDto>>> getPageableUsers(Pageable pageable);
}
