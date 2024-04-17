package io.github.spring.aop.service;

public interface InputReader extends AutoCloseable {

    int read();

    void close();

}
