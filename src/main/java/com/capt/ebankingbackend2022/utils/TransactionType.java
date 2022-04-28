package com.capt.ebankingbackend2022.utils;

public interface TransactionType {
    String DEPOSIT = "deposit";
    String WITHDRAW = "withdraw";
    String RECEIVE_TRANSFER = "receive_transfer";
    String SEND_TRANSFER = "send_transfer";
    String START_SAVING = "start_saving";
    String START_LOAN = "start_loan";
    String WITHDRAW_SAVING = "withdraw_saving";
    String RETURN_SAVING = "return_saving";
    String RETURN_LOAN = "return_loan";


    String[] TYPES = new String[]{DEPOSIT, WITHDRAW, RECEIVE_TRANSFER, SEND_TRANSFER, START_SAVING, START_LOAN, WITHDRAW_SAVING, RETURN_LOAN};
}
