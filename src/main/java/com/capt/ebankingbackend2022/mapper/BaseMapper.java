package com.capt.ebankingbackend2022.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseMapper<Entity, Dto> {
    @Autowired
    public BaseMapper( ModelMapper mapper) {
        this.mapper = mapper;
    }

    protected ModelMapper mapper;

    public abstract Entity toEntity(Dto dto);

    public abstract Dto toDto(Entity entity);
}
