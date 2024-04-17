package io.github.spring.aop.service;

import java.util.Scanner;

public class ConsoleInputReader implements InputReader {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public int read() {
        return scanner.nextInt();
    }

    @Override
    public void close() {
        scanner.close();
    }
}
