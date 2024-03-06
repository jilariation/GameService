package com.github.gameserivcespring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class LoggingAspect {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    @Around("@annotation(com.github.gameserivcespring.aspect.annotation.Logging)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalDateTime startTime = LocalDateTime.now();

        Object result = joinPoint.proceed();

        LocalDateTime endTime = LocalDateTime.now();
        long duration = java.time.Duration.between(startTime, endTime).toMillis();

        String logMessage = String.format("[%s] INFO --- [%s] : Method %s executed in %d ms",
                FORMATTER.format(startTime.atZone(ZoneId.systemDefault())),
                Thread.currentThread().getName(),
                joinPoint.getSignature(),
                duration);

        System.out.println(logMessage);

        return result;
    }
}
