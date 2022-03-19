package com.capt.ebankingbackend2022.service.impl;

import com.capt.ebankingbackend2022.utils.MMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl {
    @Autowired
    protected MMapper modelMapper;

}
