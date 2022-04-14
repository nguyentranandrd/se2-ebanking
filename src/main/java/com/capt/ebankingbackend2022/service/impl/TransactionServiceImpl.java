package com.capt.ebankingbackend2022.service.impl;

import com.capt.ebankingbackend2022.dto.TransactionDto;
import com.capt.ebankingbackend2022.dto.TransferRequestDto;
import com.capt.ebankingbackend2022.entity.AccountEntity;
import com.capt.ebankingbackend2022.entity.TransactionEntity;
import com.capt.ebankingbackend2022.entity.TransferEntity;
import com.capt.ebankingbackend2022.repository.AccountRepository;
import com.capt.ebankingbackend2022.repository.TransactionRepository;
import com.capt.ebankingbackend2022.repository.TransferRepository;
import com.capt.ebankingbackend2022.service.TransactionService;
import com.capt.ebankingbackend2022.utils.Response;
import com.capt.ebankingbackend2022.utils.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransactionServiceImpl extends BaseServiceImpl implements TransactionService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Override
    public ResponseEntity<Response<TransactionDto>> withdrawMoney(long id, double amount) {
        if (amount <= 0) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "the withdraw amount must be greater than 0"), HttpStatus.BAD_REQUEST);
        }
        AccountEntity accountEntity = accountRepository.getById(id);
        if (accountEntity.getBalance() < amount) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "the withdraw amount must be lower than the current balance"), HttpStatus.BAD_REQUEST);
        }
        TransactionEntity transaction = createTransaction(accountEntity, -amount, TransactionType.WITHDRAW);
        TransactionDto transactionDto = modelMapper.map(transaction, TransactionDto.class);
        return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, "success", transactionDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response<TransactionDto>> transferMoney(Long loggedUserId, TransferRequestDto transferRequestDto) {
        if (transferRequestDto.getAmount() <= 0) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "the transfer amount must be greater than 0"), HttpStatus.BAD_REQUEST);
        }
        AccountEntity fromAccount = accountRepository.getById(loggedUserId);
        AccountEntity toAccount = accountRepository.getById(transferRequestDto.getToAccount());
        if (fromAccount.getBalance() < transferRequestDto.getAmount()) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "the transfer amount must be lower than the current balance"), HttpStatus.BAD_REQUEST);
        }
        TransferEntity transfer = new TransferEntity();
        transfer.setCreatedAt(new Date());
        transfer.setFromAccount(fromAccount);
        transfer.setToAccount(toAccount);
        TransactionEntity toAccountTransaction = createTransaction(toAccount, transferRequestDto.getAmount(), TransactionType.RECEIVE_TRANSFER);
        TransactionEntity fromAccountTransaction = createTransaction(fromAccount, -transferRequestDto.getAmount(), TransactionType.SEND_TRANSFER);
        transfer.setToTransaction(toAccountTransaction);
        transfer.setFromTransaction(fromAccountTransaction);
        transferRepository.save(transfer);
        TransactionDto transactionDto = modelMapper.map(fromAccountTransaction, TransactionDto.class);
        return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, "success", transactionDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response<TransactionDto>> depositMoney(long loggedUserId, double amount) {
        if (amount <= 0) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "the deposit amount must be greater than 0"), HttpStatus.BAD_REQUEST);
        }
        AccountEntity accountEntity = accountRepository.getById(loggedUserId);
        TransactionEntity transaction = createTransaction(accountEntity, amount, TransactionType.DEPOSIT);
        TransactionDto transactionDto = modelMapper.map(transaction, TransactionDto.class);
        return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, "success", transactionDto), HttpStatus.OK);

    }

    private TransactionEntity createTransaction(AccountEntity accountEntity, double amount, String type) {
        TransactionEntity transaction = new TransactionEntity();
        transaction.setBalanceBefore(accountEntity.getBalance());
        accountEntity.setBalance(accountEntity.getBalance() + amount);
        accountRepository.save(accountEntity);
        transaction.setAmount(amount);
        transaction.setBalanceAfter(accountEntity.getBalance());
        transaction.setOwner(accountEntity);
        transaction.setTransactionType(type);
        transaction.setCreatedAt(new Date());
        transaction = transactionRepository.save(transaction);
        return transaction;
    }
}