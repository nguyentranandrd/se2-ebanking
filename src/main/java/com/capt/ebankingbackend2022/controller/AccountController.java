package com.capt.ebankingbackend2022.controller;

import com.capt.ebankingbackend2022.dto.AccountDto;
import com.capt.ebankingbackend2022.dto.UserDto;
import com.capt.ebankingbackend2022.service.AccountService;
import com.capt.ebankingbackend2022.service.DropboxService;
import com.capt.ebankingbackend2022.utils.Response;
import com.dropbox.core.DbxException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
@Slf4j
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private DropboxService dropboxService;

    @GetMapping("/ping")
    public Response<String> pingServer() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());
        return new Response<>(0, "ping success");
    }


    @PostMapping("/update/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Response<AccountDto>> updateUserInfo(@PathVariable("id") Long accountId, @RequestBody UserDto userDto) {
        return accountService.updateUserInfo(accountId, userDto);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Response<AccountDto>> getUserByAccountId(@PathVariable Long id) {
        return accountService.getUserByAccountId(id);
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<Response<AccountDto>> getLoggedUserInfo() {
        return accountService.getLoggedUserInfo();
    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/password/change")
    public ResponseEntity<Response<Boolean>> changePassword(@RequestPart("previous_pass") String previousPass, @RequestPart("new_password") String newPassword) {
        return accountService.changePassword(previousPass, newPassword);
    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/avatar")
    public ResponseEntity<Response<String>> changeAvatar(@RequestPart("avatar") MultipartFile avatar) {
        try {
            AccountDto account = accountService.getLoggedUserInfo().getBody().getData();
            if (account.getUser() == null) {
                return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "fail", "Please upload user info first"), HttpStatus.BAD_REQUEST);
            }
            String path = dropboxService.uploadFile(avatar, "/user/avatar/" + account.getId() + ".png");
            path = path.substring(0, path.length() - 1) + "1";
            accountService.updateAvatar(path);
            return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, "success", path), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "fail", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Response<Page<AccountDto>>> getUsers(Pageable pageable) {
        return accountService.getPageableUsers(pageable);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Response<Boolean>> deleteAccount(@PathVariable("id") Long id) {
        String avt = accountService.getUserAvatar(id);
        ResponseEntity<Response<Boolean>> res = accountService.deleteAccount(id);
        if (res.getBody().getStatus() == Response.STATUS_SUCCESS && avt != null) {
            try {
                dropboxService.deleteFile("/user/avatar/" + id);
            } catch (DbxException e) {
                e.printStackTrace();
            }
        }
        return res;
    }


    @PatchMapping("/{id}/balance")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Response<Boolean>> setAccountBalance(@PathVariable("id") Long id, @RequestPart("balance") String balance) {
        double balanceNumber = Double.parseDouble(balance);
        return accountService.updateAccountBalance(id, balanceNumber);
    }
}
