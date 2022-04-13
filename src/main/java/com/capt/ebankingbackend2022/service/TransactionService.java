package com.capt.ebankingbackend2022.service;

import com.capt.ebankingbackend2022.dto.TransactionDto;
import com.capt.ebankingbackend2022.utils.Response;
import org.springframework.http.ResponseEntity;

public interface TransactionService {

    ResponseEntity<Response<TransactionDto>> depositMoney(long id, double amount);

    ResponseEntity<Response<TransactionDto>> withdrawMoney(long loggedUserId, double amount);
}
