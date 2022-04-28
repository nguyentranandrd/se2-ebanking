package com.capt.ebankingbackend2022.service.impl;

import com.capt.ebankingbackend2022.dto.CreateLoanDto;
import com.capt.ebankingbackend2022.dto.CreateSavingDto;
import com.capt.ebankingbackend2022.dto.TransactionDto;
import com.capt.ebankingbackend2022.dto.TransferRequestDto;
import com.capt.ebankingbackend2022.entity.*;
import com.capt.ebankingbackend2022.mapper.TransactionMapper;
import com.capt.ebankingbackend2022.repository.*;
import com.capt.ebankingbackend2022.service.TransactionService;
import com.capt.ebankingbackend2022.utils.InterestType;
import com.capt.ebankingbackend2022.utils.Response;
import com.capt.ebankingbackend2022.utils.TransactionStatus;
import com.capt.ebankingbackend2022.utils.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionServiceImpl extends BaseServiceImpl implements TransactionService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private InterestRepository interestRepository;

    @Autowired
    private SavingRepository savingRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private TransactionMapper transactionMapper;


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
    public ResponseEntity<Response<TransactionDto>> transferMoney(long loggedUserId, TransferRequestDto transferRequestDto) {
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
        fromAccountTransaction.setFromTransfer(transfer);
        TransactionDto transactionDto = transactionMapper.toDto(fromAccountTransaction);
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


    @Override
    public ResponseEntity<Response<TransactionDto>> createSaving(long loggedUserId, CreateSavingDto createSavingDto) {
        if (createSavingDto.getAmount() <= 0) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "the saving amount must be greater than 0"), HttpStatus.BAD_REQUEST);
        }
        AccountEntity accountEntity = accountRepository.getById(loggedUserId);
        if (accountEntity.getBalance() < createSavingDto.getAmount()) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "the saving amount must be lower than the current balance"), HttpStatus.BAD_REQUEST);
        }
        InterestEntity interestEntity = interestRepository.findById(createSavingDto.getInterestId()).orElse(null);

        if (interestEntity == null) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "interest is not found"), HttpStatus.BAD_REQUEST);
        }
        if (!interestEntity.getType().equals(InterestType.SAVING)) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "interest type must be saving"), HttpStatus.BAD_REQUEST);
        }
        TransactionEntity transaction = createTransaction(accountEntity, -createSavingDto.getAmount(), TransactionType.START_SAVING);
        SavingEntity savingEntity = new SavingEntity();
        savingEntity.setCreatedAt(new Date());
        savingEntity.setStartTime(new Date());
        Date endDate = new Date(System.currentTimeMillis() + interestEntity.getDuration());
        savingEntity.setEndTime(endDate);
        savingEntity.setInterest(interestEntity);
        savingEntity.setHasMaturity(createSavingDto.isHasMaturity());
        savingEntity.setMaturityWithProfit(createSavingDto.isMaturityWithProfit());
        savingEntity.setTransaction(transaction);
        savingEntity.setStatus(TransactionStatus.IN_PROGRESS);
        savingEntity = savingRepository.save(savingEntity);
        transaction.setSaving(savingEntity);
        return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, "create saving successfully",
                transactionMapper.toDto(transaction)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response<TransactionDto>> createLoan(long loggedUserId, CreateLoanDto createLoanDto) {
        if (createLoanDto.getAmount() <= 0) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "the loan amount must be greater than 0"), HttpStatus.BAD_REQUEST);
        }
        AccountEntity accountEntity = accountRepository.getById(loggedUserId);
        InterestEntity interestEntity = interestRepository.findById(createLoanDto.getInterestId()).orElse(null);
        if (interestEntity == null) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "interest is not found"), HttpStatus.BAD_REQUEST);
        }
        if (!interestEntity.getType().equals(InterestType.LOAN)) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "interest type must be loan"), HttpStatus.BAD_REQUEST);
        }
        TransactionEntity transaction = createTransaction(accountEntity, createLoanDto.getAmount(), TransactionType.START_LOAN);
        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setCreatedAt(new Date());
        loanEntity.setStartTime(new Date());
        Date endDate = new Date(System.currentTimeMillis() + interestEntity.getDuration());
        loanEntity.setEndTime(endDate);
        loanEntity.setInterest(interestEntity);
        loanEntity.setTransaction(transaction);
        loanEntity.setStatus(TransactionStatus.IN_PROGRESS);
        loanEntity = loanRepository.save(loanEntity);
        transaction.setLoan(loanEntity);
        return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, "create loan successfully",
                transactionMapper.toDto(transaction)), HttpStatus.OK);

    }

    @Override
    @Transactional
    public void resolveEndTimeSavingAndLoan() {
        List<LoanEntity> completedLoans = loanRepository.findByStatusAndEndTimeLessThanEqual(TransactionStatus.IN_PROGRESS, new Date());
        for (LoanEntity loan :
                completedLoans) {
            loan.setStatus(TransactionStatus.OVER_DUE);
            loanRepository.save(loan);
        }
        List<SavingEntity> completedSavings = savingRepository.findByStatusAndEndTimeLessThanEqual(TransactionStatus.IN_PROGRESS, new Date());
        for (SavingEntity saving :
                completedSavings) {
            AccountEntity owner = saving.getTransaction().getOwner();
            InterestEntity interest = saving.getInterest();
            saving.setStatus(TransactionStatus.COMPLETED);
            double profit = saving.getTransaction().getAmount() * (interest.getDuration() / (double) TimeUnit.DAYS.toMillis(365));
            if (!saving.isHasMaturity()) {
                double moneyChanged = saving.getTransaction().getAmount() + profit;
                createTransaction(owner, moneyChanged, TransactionType.RETURN_SAVING);
            } else {
                double amount = saving.getTransaction().getAmount();
                if (saving.isMaturityWithProfit()) {
                    amount += profit;
                } else {
                    createTransaction(owner, profit, TransactionType.RETURN_SAVING);
                }
                createSaving(owner.getId(), new CreateSavingDto(amount, saving.isHasMaturity(), saving.isMaturityWithProfit(), saving.getInterest().getId()));
            }
            accountRepository.save(owner);
            savingRepository.save(saving);
        }
    }


    private TransactionEntity createTransaction(AccountEntity accountEntity, double changingAmount, String type) {
        TransactionEntity transaction = new TransactionEntity();
        transaction.setBalanceBefore(accountEntity.getBalance());
        accountEntity.setBalance(accountEntity.getBalance() + changingAmount);
        accountRepository.save(accountEntity);
        transaction.setAmount(Math.abs(changingAmount));
        transaction.setBalanceAfter(accountEntity.getBalance());
        transaction.setOwner(accountEntity);
        transaction.setTransactionType(type);
        transaction.setCreatedAt(new Date());
        transaction = transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public ResponseEntity<Response<Page<TransactionDto>>> getPageableTransaction(long userId, Pageable pageable, String type) {
        if (type != null && !Arrays.asList(TransactionType.TYPES).contains(type)) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "Invalid transaction type"), HttpStatus.BAD_REQUEST);
        }
        Page<TransactionDto> page;
        if (type == null)
            page = transactionRepository.getByOwnerId(userId, pageable).map(transactionEntity -> transactionMapper.toDto(transactionEntity));
        else
            page = transactionRepository.getByOwnerIdAndTransactionType(userId, type, pageable).map(transactionEntity -> transactionMapper.toDto(transactionEntity));
        return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, "success", page), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response<TransactionDto>> withdrawSaving(long loggedUserId, long savingId) {
        AccountEntity account = accountRepository.getById(loggedUserId);
        SavingEntity savingEntity = savingRepository.findById(savingId).orElse(null);
        if (savingEntity == null)
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "Invalid saving id"), HttpStatus.BAD_REQUEST);
        if (savingEntity.getTransaction().getOwner().getId() != account.getId())
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "Not the saving owner"), HttpStatus.BAD_REQUEST);
        if (savingEntity.getStatus().equals(TransactionStatus.COMPLETED)) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "Saving has already been deposited"), HttpStatus.BAD_REQUEST);
        }
        savingEntity.setStatus(TransactionStatus.COMPLETED);
        double interestMoney = savingEntity.getTransaction().getAmount() * savingEntity.getInterest().getInstantRate() * (float) (System.currentTimeMillis() - savingEntity.getStartTime().getTime()) / (float) TimeUnit.DAYS.toMillis(365);
        double moneyChanged = interestMoney + savingEntity.getTransaction().getAmount();
        savingRepository.save(savingEntity);
        TransactionEntity transactionEntity = createTransaction(account, moneyChanged, TransactionType.WITHDRAW_SAVING);
        return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, "success", transactionMapper.toDto(transactionEntity)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response<TransactionDto>> payLoan(long loggedUserId, long loanId) {
        AccountEntity account = accountRepository.getById(loggedUserId);
        LoanEntity loan = loanRepository.findById(loanId).orElse(null);
        if (loan == null)
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "Invalid loan id"), HttpStatus.BAD_REQUEST);
        if (!Objects.equals(loan.getTransaction().getOwner().getId(), account.getId()))
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "Not the loan owner"), HttpStatus.BAD_REQUEST);
        if (loan.getStatus().equals(TransactionStatus.COMPLETED)) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "Loan has already been returned"), HttpStatus.BAD_REQUEST);
        }
        double interestMoney = loan.getTransaction().getAmount() * loan.getInterest().getRate() * (System.currentTimeMillis() - loan.getStartTime().getTime()) / (float) TimeUnit.DAYS.toMillis(365);
        double moneyChange = loan.getTransaction().getAmount() + interestMoney;
        if (account.getBalance()< moneyChange){
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "Account balance not enough to return loan"), HttpStatus.BAD_REQUEST);
        }
        loan.setStatus(TransactionStatus.COMPLETED);
        loanRepository.save(loan);
        TransactionEntity transaction = createTransaction(account, -moneyChange, TransactionType.RETURN_LOAN);
        return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, "success", transactionMapper.toDto(transaction)), HttpStatus.OK);

    }
}
