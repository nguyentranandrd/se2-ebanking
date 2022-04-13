package com.capt.ebankingbackend2022.controller;

import com.capt.ebankingbackend2022.dto.TransactionDto;
import com.capt.ebankingbackend2022.service.AccountService;
import com.capt.ebankingbackend2022.service.TransactionService;
import com.capt.ebankingbackend2022.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

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
}
