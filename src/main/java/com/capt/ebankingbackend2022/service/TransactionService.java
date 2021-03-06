package com.capt.ebankingbackend2022.service;

import com.capt.ebankingbackend2022.dto.CreateLoanDto;
import com.capt.ebankingbackend2022.dto.CreateSavingDto;
import com.capt.ebankingbackend2022.dto.TransactionDto;
import com.capt.ebankingbackend2022.dto.TransferRequestDto;
import com.capt.ebankingbackend2022.utils.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface TransactionService {

    ResponseEntity<Response<TransactionDto>> depositMoney(long id, double amount);

    ResponseEntity<Response<TransactionDto>> withdrawMoney(long loggedUserId, double amount);

    ResponseEntity<Response<TransactionDto>> transferMoney(long loggedUserId, TransferRequestDto transferRequestDto);

    ResponseEntity<Response<TransactionDto>> createSaving(long loggedUserId, CreateSavingDto createSavingDto);

    ResponseEntity<Response<TransactionDto>> createLoan(long loggedUserId, CreateLoanDto createLoanDto);

    void resolveEndTimeSavingAndLoan();

    ResponseEntity<Response<Page<TransactionDto>>> getPageableTransaction(long id, Pageable pageable, String type);

    ResponseEntity<Response<TransactionDto>> withdrawSaving(long loggedUserId, long savingId);

    ResponseEntity<Response<TransactionDto>> payLoan(long loggedUserId, long loanId);
}
