package com.wt.test.wolverine.interfaces;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author qiyu
 * @since 2023/6/6
 */
@SpringBootApplication(scanBasePackages = {"com.wt.test.wolverine","org.nebula"})
@EnableConfigurationProperties
public class WolverineApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(WolverineApplication.class, args);
    }
}
