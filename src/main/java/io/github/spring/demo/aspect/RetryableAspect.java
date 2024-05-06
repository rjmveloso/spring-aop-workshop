package io.github.spring.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class RetryableAspect {

    private static final int DEFAULT_MAX_RETRIES = 2;
    private int maxRetries = DEFAULT_MAX_RETRIES;

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }


    @Around("execution(int io.github.spring.demo.service.NumberGenerator.*(..)) && " +
            "@target(io.github.spring.demo.Retryable)")
    public Object execute(ProceedingJoinPoint pjp) throws Throwable {
        var numAttempts = 0;
        do {
            Object result = pjp.proceed();
            if (!(result instanceof Integer)) {
                throw new UnsupportedOperationException(
                        "Invalid data type: %s".formatted(result.getClass().getSimpleName()));
            }
            if ((int) result > 50) {
                return result;
            }
        } while (++numAttempts <= this.maxRetries);

        return 0;
    }
}
