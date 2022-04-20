package com.capt.ebankingbackend2022.mapper;

import com.capt.ebankingbackend2022.dto.InterestDto;
import com.capt.ebankingbackend2022.dto.SavingDto;
import com.capt.ebankingbackend2022.entity.InterestEntity;
import com.capt.ebankingbackend2022.entity.SavingEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SavingMapper extends BaseMapper<SavingEntity, SavingDto> {

    @Autowired
    public SavingMapper(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    public SavingEntity toEntity(SavingDto savingDto) {
        InterestEntity interestEntity = mapper.map(savingDto.getInterest(), InterestEntity.class);
        SavingEntity savingEntity = mapper.map(savingDto, SavingEntity.class);
        savingEntity.setInterest(interestEntity);
        return savingEntity;
    }

    @Override
    public SavingDto toDto(SavingEntity savingEntity) {
        InterestDto interestDto = mapper.map(savingEntity.getInterest(), InterestDto.class);
        SavingDto savingDto = mapper.map(savingEntity, SavingDto.class);
        savingDto.setInterest(interestDto);
        return savingDto;
    }
}
