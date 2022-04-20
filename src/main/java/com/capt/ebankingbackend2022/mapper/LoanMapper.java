package com.capt.ebankingbackend2022.mapper;

import com.capt.ebankingbackend2022.dto.InterestDto;
import com.capt.ebankingbackend2022.dto.LoanDto;
import com.capt.ebankingbackend2022.dto.SavingDto;
import com.capt.ebankingbackend2022.entity.InterestEntity;
import com.capt.ebankingbackend2022.entity.LoanEntity;
import com.capt.ebankingbackend2022.entity.SavingEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper extends BaseMapper<LoanEntity, LoanDto> {

    @Autowired
    public LoanMapper(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    public LoanEntity toEntity(LoanDto loanDto) {
        InterestEntity interestEntity = mapper.map(loanDto.getInterest(), InterestEntity.class);
        LoanEntity LoanEntity = mapper.map(loanDto, LoanEntity.class);
        LoanEntity.setInterest(interestEntity);
        return LoanEntity;
    }

    @Override
    public LoanDto toDto(LoanEntity savingEntity) {
        InterestDto interestDto = mapper.map(savingEntity.getInterest(), InterestDto.class);
        LoanDto loanDto = mapper.map(savingEntity, LoanDto.class);
        loanDto.setInterest(interestDto);
        return loanDto;
    }
}
