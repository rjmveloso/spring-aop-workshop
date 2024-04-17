package io.github.spring.aop;

import io.github.spring.aop.service.*;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GamblingDemo implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GamblingDemo.class, args);
    }

    private final ConsoleGame game;

    public GamblingDemo(AutowireCapableBeanFactory beanFactory) {
        this.game = new ConsoleGame(beanFactory.getBean(GameRuler.class));
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starting AopDemo application");

        game.run();
    }

    private static class ConsoleGame implements Runnable {
        private final GameRuler ruler;

        private ConsoleGame(GameRuler ruler) {
            this.ruler = ruler;
        }

        @Override
        public void run() {
            System.out.println(ruler.rules());

            try(InputReader reader = new ConsoleInputReader()) {
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
}