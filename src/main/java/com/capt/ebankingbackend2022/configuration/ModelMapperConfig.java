package com.capt.ebankingbackend2022.configuration;

import com.capt.ebankingbackend2022.utils.MMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public MMapper provideModelMapper() {
        return new MMapper();
    }

}
