package org.example.superheroe.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.superheroe.controller.SuperheroeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceCode {
    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceCode.class);

    /**
     * Print into log file
     */
    @Around("@annotation(Performance)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

       LOGGER.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}
