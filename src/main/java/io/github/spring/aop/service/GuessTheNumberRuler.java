package io.github.spring.aop.service;

import io.github.spring.aop.repository.SentenceGenerator;

public class GuessTheNumberRuler implements GameRuler {

    private final int number;

    private int numberOfAttempts;

    private final SentenceGenerator sentenceGenerator;

    public GuessTheNumberRuler(NumberGenerator numberGenerator, SentenceGenerator sentenceGenerator, int numberOfAttempts) {
        this.sentenceGenerator = sentenceGenerator;
        this.number = numberGenerator.roll();
        this.numberOfAttempts = numberOfAttempts;
    }

    @Override
    public String check(int value) {
        if (numberOfAttempts == 0) {
            return "LOOSE";
        } else if (value == number) {
            return "WIN";
        } else {
            numberOfAttempts--;
            return sentenceGenerator.generate();
        }
    }

    @Override
    public String rules() {
        return "Guess the number\nNumber of attempts: " + numberOfAttempts;
    }
}
