package io.github.spring.aop.service;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {

    @Override
    public int roll() {
        return new Random().nextInt(6) + 1;
    }
}
