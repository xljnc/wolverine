package com.wt.test.wolverine.infra.executor.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 公共线程池
 *
 * @author qiyu
 * @since 2023/7/6
 */
@Configuration
@ConfigurationProperties("common.executor")
public class CommonExecutorConfig {
    
    private int coreSize = 5;
    private int maxSize = 10;
    private long keepAliveTime = 3L;
    
    private Integer workQueueSize = 1000;
    
    @Bean("commonExecutor")
    public ThreadPoolExecutor commonExecutor() {
        ThreadPoolExecutor commonExecutor = new ThreadPoolExecutor(coreSize, maxSize, keepAliveTime, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(workQueueSize), new ThreadPoolExecutor.CallerRunsPolicy());
        commonExecutor.prestartAllCoreThreads();
        return commonExecutor;
    }
}
