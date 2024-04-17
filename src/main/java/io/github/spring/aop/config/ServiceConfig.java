package io.github.spring.aop.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.spring.aop.repository.ChuckNorrisSentenceGenerator;
import io.github.spring.aop.repository.SentenceGenerator;
import io.github.spring.aop.service.*;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public InputReader inputReader() {
        return new ConsoleInputReader();
    }

    @Bean
    public GameRuler gameRuler(SentenceGenerator sentenceGenerator) {
        return new GuessTheNumberRuler(numberGenerator(), sentenceGenerator,5);
    }

    @Bean
    public NumberGenerator numberGenerator() {
        return new RandomNumberGenerator();
    }

    @Bean
    public SentenceGenerator sentenceGenerator(OkHttpClient client) {
        return new ChuckNorrisSentenceGenerator(client, new ObjectMapper());
    }
}
