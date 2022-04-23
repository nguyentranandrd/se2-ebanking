package com.capt.ebankingbackend2022.controller;

import com.capt.ebankingbackend2022.dto.*;
import com.capt.ebankingbackend2022.service.AccountService;
import com.capt.ebankingbackend2022.service.TransactionService;
import com.capt.ebankingbackend2022.utils.Response;
import org.h2.mvstore.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/deposit")
    @PreAuthorize("hasAnyAuthority('user', 'admin')")
    public ResponseEntity<Response<TransactionDto>> depositMoney(@RequestPart("amount") String amountString) {
        Long loggedUserId = accountService.getLoggedUserInfo().getBody().getData().getId();
        double amount = Double.parseDouble(amountString);
        return transactionService.depositMoney(loggedUserId, amount);
    }

    @PostMapping("/withdraw")
    @PreAuthorize("hasAnyAuthority('user', 'admin')")
    public ResponseEntity<Response<TransactionDto>> withdrawMoney(@RequestPart("amount") String amountString) {
        Long loggedUserId = accountService.getLoggedUserInfo().getBody().getData().getId();
        double amount = Double.parseDouble(amountString);
        return transactionService.withdrawMoney(loggedUserId, amount);
    }

    @PostMapping("/transfer")
    @PreAuthorize("hasAnyAuthority('user', 'admin')")
    public ResponseEntity<Response<TransactionDto>> transferMoney(@RequestBody TransferRequestDto transferRequestDto) {
        Long loggedUserId = accountService.getLoggedUserInfo().getBody().getData().getId();
        return transactionService.transferMoney(loggedUserId, transferRequestDto);
    }

    @PostMapping("/saving")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Response<TransactionDto>> createSaving(@RequestBody CreateSavingDto createSavingDto) {
        Long loggedUserId = accountService.getLoggedUserInfo().getBody().getData().getId();
        return transactionService.createSaving(loggedUserId, createSavingDto);
    }

    @PostMapping("/loan")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Response<TransactionDto>> createLoan(@RequestBody CreateLoanDto createLoanDto) {
        Long loggedUserId = accountService.getLoggedUserInfo().getBody().getData().getId();
        return transactionService.createLoan(loggedUserId, createLoanDto);
    }


    public ResponseEntity<Response<Page>> getPageableTransactions(){
        return null;
    }
}
