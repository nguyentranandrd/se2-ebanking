package com.capt.ebankingbackend2022.mapper;

import com.capt.ebankingbackend2022.dto.LoanDto;
import com.capt.ebankingbackend2022.dto.SavingDto;
import com.capt.ebankingbackend2022.dto.TransactionDto;
import com.capt.ebankingbackend2022.entity.TransactionEntity;
import com.capt.ebankingbackend2022.utils.TransactionType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper extends BaseMapper<TransactionEntity, TransactionDto> {
    @Autowired
    private SavingMapper savingMapper;

    @Autowired
    private LoanMapper loanMapper;

    @Autowired
    public TransactionMapper(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    public TransactionEntity toEntity(TransactionDto transactionDto) {
        return null;
    }

    @Override
    public TransactionDto toDto(TransactionEntity transactionEntity) {
        TransactionDto transactionDto = mapper.map(transactionEntity, TransactionDto.class);
        switch (transactionDto.getTransactionType()) {
            case TransactionType.START_SAVING:
                if (transactionEntity.getSaving() != null) {
                    SavingDto savingDto = savingMapper.toDto(transactionEntity.getSaving());
                    transactionDto.setSaving(savingDto);
                }
                break;
            case TransactionType.START_LOAN:
                if (transactionEntity.getLoan() != null) {
                    LoanDto savingDto = loanMapper.toDto(transactionEntity.getLoan());
                    transactionDto.setLoan(savingDto);
                }
                break;
        }
        return transactionDto;
    }
}
