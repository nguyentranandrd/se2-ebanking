package com.capt.ebankingbackend2022.mapper;

import com.capt.ebankingbackend2022.dto.AccountDto;
import com.capt.ebankingbackend2022.dto.RoleDto;
import com.capt.ebankingbackend2022.dto.UserDto;
import com.capt.ebankingbackend2022.entity.AccountEntity;
import com.capt.ebankingbackend2022.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper extends BaseMapper<UserEntity, UserDto> {
    @Autowired
    public UserMapper(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    public UserEntity toEntity(UserDto userDto) {
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        return userEntity;
    }

    @Override
    public UserDto toDto(UserEntity userEntity) {
        UserDto userDto = mapper.map(userEntity, UserDto.class);
        AccountEntity accountEntity = userEntity.getAccount();
        AccountDto accountDto = mapper.map(accountEntity, AccountDto.class);
        List<RoleDto> roleDtos = accountEntity.getRoles().stream().map(roleEntity -> mapper.map(roleEntity, RoleDto.class)).collect(Collectors.toList());
        accountDto.setRoles(roleDtos);
        userDto.setAccount(accountDto);
        return userDto;
    }
}
