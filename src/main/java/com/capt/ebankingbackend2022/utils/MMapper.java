package com.capt.ebankingbackend2022.utils;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class MMapper extends ModelMapper {

    public <D> List<D> map(List<Object> sources, Class<D> destinationType) {
        List<D> listData = new ArrayList<>();
        for (Object source :
                sources) {
            listData.add(map(source, destinationType));
        }
        return listData;
    }


}
