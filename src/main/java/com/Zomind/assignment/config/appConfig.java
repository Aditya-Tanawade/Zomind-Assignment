package com.Zomind.assignment.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class appConfig {

    @Bean
    public ModelMapper getModdelMapper(){
        return new ModelMapper();
    }
}
