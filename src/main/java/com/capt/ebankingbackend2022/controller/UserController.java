package com.capt.ebankingbackend2022.controller;

import com.capt.ebankingbackend2022.dto.UserDto;
import com.capt.ebankingbackend2022.service.UserService;
import com.capt.ebankingbackend2022.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/ping")
    public Response<String> pingServer() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());
        return new Response<>(0, "ping success");
    }


    @PostMapping("/update/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Response<UserDto>> updateUserInfo(@PathVariable("id") Long accountId, @RequestBody UserDto userDto) {
        return userService.updateUserInfo(accountId, userDto);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<UserDto> getLoggedUserInfo() {
        return userService.getLoggedUserInfo();
    }

}