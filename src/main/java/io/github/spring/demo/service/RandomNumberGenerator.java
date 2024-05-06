package io.github.spring.demo.service;

import io.github.spring.demo.Retryable;

import java.util.Random;

@Retryable
public class RandomNumberGenerator implements NumberGenerator {

    @Override
    public int generate() {
        return new Random().nextInt(100);
    }

}
