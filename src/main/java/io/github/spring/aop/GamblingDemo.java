package io.github.spring.aop;

import io.github.spring.aop.layout.ConsoleGameLayout;
import io.github.spring.aop.layout.GameLayout;
import io.github.spring.aop.service.GameRuler;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GamblingDemo implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GamblingDemo.class, args);
    }

    private final GameLayout game;

    public GamblingDemo(AutowireCapableBeanFactory beanFactory) {
        this.game = new ConsoleGameLayout(beanFactory.getBean(GameRuler.class));
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starting GamblingDemo application");
        game.run();
    }

}