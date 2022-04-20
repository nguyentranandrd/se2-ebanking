package com.capt.ebankingbackend2022.utils;

public interface TransactionType {
    String DEPOSIT = "deposit";
    String WITHDRAW = "withdraw";
    String RECEIVE_TRANSFER = "receive_transfer";
    String SEND_TRANSFER = "send_transfer";
    String START_SAVING = "start_saving";
    String START_LOAN = "start_loan";
}
