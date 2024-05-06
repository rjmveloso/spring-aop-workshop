package io.github.spring.demo.config;

import io.github.spring.demo.service.NumberGenerator;
import io.github.spring.demo.service.RandomNumberGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public NumberGenerator numberGenerator() {
        return new RandomNumberGenerator();
    }

}
