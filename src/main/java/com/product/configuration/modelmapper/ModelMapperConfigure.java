package com.product.configuration.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class ModelMapperConfigure {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    
}
