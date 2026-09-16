package com.example.datingapp;

import com.example.datingapp.service.CompatibilityCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    @Scope("prototype")
    public CompatibilityCalculator compatibilityCalculator() {
        return new CompatibilityCalculator();
    }
}