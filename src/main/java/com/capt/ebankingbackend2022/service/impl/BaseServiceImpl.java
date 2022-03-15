package com.capt.ebankingbackend2022.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl {
    @Autowired
    protected ModelMapper modelMapper;

}
