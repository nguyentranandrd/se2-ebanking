package com.capt.ebankingbackend2022.service;

import com.capt.ebankingbackend2022.dto.CreateLoanDto;
import com.capt.ebankingbackend2022.dto.CreateSavingDto;
import com.capt.ebankingbackend2022.dto.TransactionDto;
import com.capt.ebankingbackend2022.dto.TransferRequestDto;
import com.capt.ebankingbackend2022.utils.Response;
import org.springframework.http.ResponseEntity;

public interface TransactionService {

    ResponseEntity<Response<TransactionDto>> depositMoney(long id, double amount);

    ResponseEntity<Response<TransactionDto>> withdrawMoney(long loggedUserId, double amount);

    ResponseEntity<Response<TransactionDto>> transferMoney(long loggedUserId, TransferRequestDto transferRequestDto);

    ResponseEntity<Response<TransactionDto>> createSaving(long loggedUserId, CreateSavingDto createSavingDto);

    ResponseEntity<Response<TransactionDto>> createLoan(Long loggedUserId, CreateLoanDto createLoanDto);

    void resolveEndTimeSavingAndLoan();
}
