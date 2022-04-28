package com.capt.ebankingbackend2022.controller;

import com.capt.ebankingbackend2022.dto.CreateLoanDto;
import com.capt.ebankingbackend2022.dto.CreateSavingDto;
import com.capt.ebankingbackend2022.dto.TransactionDto;
import com.capt.ebankingbackend2022.dto.TransferRequestDto;
import com.capt.ebankingbackend2022.service.AccountService;
import com.capt.ebankingbackend2022.service.TransactionService;
import com.capt.ebankingbackend2022.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/saving/withdraw/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Response<TransactionDto>> withdrawSaving(@PathVariable("id") Long savingId) {
        Long loggedUserId = accountService.getLoggedUserInfo().getBody().getData().getId();
        return transactionService.withdrawSaving(loggedUserId, savingId);
    }


    @PostMapping("/loan")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Response<TransactionDto>> createLoan(@RequestBody CreateLoanDto createLoanDto) {
        Long loggedUserId = accountService.getLoggedUserInfo().getBody().getData().getId();
        return transactionService.createLoan(loggedUserId, createLoanDto);
    }

    @GetMapping("/loan/return/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Response<TransactionDto>> payLoan(@PathVariable("id") Long loanId) {
        Long loggedUserId = accountService.getLoggedUserInfo().getBody().getData().getId();
        return transactionService.payLoan(loggedUserId, loanId);
    }

    @GetMapping("/histories/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Response<Page<TransactionDto>>> getPageableTransactions(@PathVariable("id") long id, Pageable pageable, @RequestParam(required = false) String type) {
        long userId = id;
        return transactionService.getPageableTransaction(userId, pageable, type);
    }

    @GetMapping("/histories/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Response<Page<TransactionDto>>> getMyPageableTransactions(Pageable pageable, @RequestParam(required = false) String type) {
        Long loggedUserId = accountService.getLoggedUserInfo().getBody().getData().getId();
        return transactionService.getPageableTransaction(loggedUserId, pageable, type);
    }
}
