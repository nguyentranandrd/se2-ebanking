package com.capt.ebankingbackend2022.service;

import com.capt.ebankingbackend2022.dto.UserDto;
import com.capt.ebankingbackend2022.utils.Response;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Response<UserDto>> updateUserInfo(Long accountId, UserDto userDto);

    ResponseEntity<UserDto> getLoggedUserInfo();
}
