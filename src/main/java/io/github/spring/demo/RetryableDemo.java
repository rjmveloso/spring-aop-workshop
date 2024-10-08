package io.github.spring.demo;

import io.github.spring.demo.service.NumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class RetryableDemo implements CommandLineRunner {

    @Autowired
    private NumberGenerator numberGenerator;

    public static void main(String[] args) {
        SpringApplication.run(RetryableDemo.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(numberGenerator.generate());
    }

}
