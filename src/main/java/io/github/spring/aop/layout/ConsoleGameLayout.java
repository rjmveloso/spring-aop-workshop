package io.github.spring.aop.layout;

import io.github.spring.aop.service.ConsoleInputReader;
import io.github.spring.aop.service.GameRuler;
import io.github.spring.aop.service.InputReader;

public class ConsoleGameLayout implements GameLayout {

    private final GameRuler ruler;

    public ConsoleGameLayout(GameRuler ruler) {
        this.ruler = ruler;
    }

    @Override
    public void run() {
        System.out.println(ruler.rules());

        try (InputReader reader = new ConsoleInputReader()) {
            while (true) {
                int number = reader.read();
                String result = ruler.check(number);

                switch (result) {
                    case "LOOSE" -> {
                        System.out.println("LOOSER");
                        return;
                    }
                    case "WIN" -> {
                        System.out.println("WINNER");
                        return;
                    }
                    default -> System.out.println(result);
                }
            }
        }
    }

}
