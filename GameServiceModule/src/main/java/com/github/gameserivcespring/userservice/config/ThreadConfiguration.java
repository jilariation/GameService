package com.github.gameserivcespring.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class ThreadConfiguration {
    @Bean
    public ExecutorService threadExecutor() {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(500);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 60L,
                TimeUnit.SECONDS, queue, new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }
}
