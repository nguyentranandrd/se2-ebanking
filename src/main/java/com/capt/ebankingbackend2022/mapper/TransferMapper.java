package com.capt.ebankingbackend2022.mapper;

import com.capt.ebankingbackend2022.dto.AccountDto;
import com.capt.ebankingbackend2022.dto.TransferAccountDto;
import com.capt.ebankingbackend2022.dto.TransferDto;
import com.capt.ebankingbackend2022.entity.AccountEntity;
import com.capt.ebankingbackend2022.entity.TransferEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferMapper extends BaseMapper<TransferEntity, TransferDto> {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    public TransferMapper(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    public TransferEntity toEntity(TransferDto transferDto) {
        return null;
    }

    @Override
    public TransferDto toDto(TransferEntity transferEntity) {
        TransferDto transferDto = new TransferDto();
        TransferAccountDto toAccount = createTransferAccount(transferEntity.getToAccount());
        TransferAccountDto fromAccount = createTransferAccount(transferEntity.getFromAccount());
        transferDto.setToAccount(toAccount);
        transferDto.setFromAccount(fromAccount);
        return transferDto;
    }

    private TransferAccountDto createTransferAccount(AccountEntity accountEntity) {
        TransferAccountDto account = new TransferAccountDto();
        account.setId(accountEntity.getId());

        account.setPhoneNo(accountEntity.getPhoneNumber());
        if (accountEntity.getUser() != null) {
            account.setFirstName(accountEntity.getUser().getFirstName());
            account.setLastName(accountEntity.getUser().getLastName());
            account.setEmail(accountEntity.getUser().getEmail());
        }
        return account;
    }

}
