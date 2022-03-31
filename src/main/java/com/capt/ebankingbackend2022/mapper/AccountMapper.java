package com.capt.ebankingbackend2022.mapper;

import com.capt.ebankingbackend2022.dto.AccountDto;
import com.capt.ebankingbackend2022.dto.RoleDto;
import com.capt.ebankingbackend2022.dto.UserDto;
import com.capt.ebankingbackend2022.entity.AccountEntity;
import com.capt.ebankingbackend2022.entity.RoleEntity;
import com.capt.ebankingbackend2022.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AccountMapper extends BaseMapper<AccountEntity, AccountDto> {
    @Autowired
    public AccountMapper(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    public AccountEntity toEntity(AccountDto accountDto) {
        AccountEntity entity = mapper.map(accountDto, AccountEntity.class);
        return entity;
    }

    @Override
    public AccountDto toDto(AccountEntity accountEntity) {
        AccountDto dto = mapper.map(accountEntity, AccountDto.class);
        List<RoleDto> roleDtos = accountEntity.getRoles().stream().map(roleEntity -> mapper.map(roleEntity, RoleDto.class)).collect(Collectors.toList());
        dto.setRoles(roleDtos);
        if (accountEntity.getUser() != null) {
            dto.setUser(mapper.map(accountEntity.getUser(), UserDto.class));
        }
        return dto;
    }
}
