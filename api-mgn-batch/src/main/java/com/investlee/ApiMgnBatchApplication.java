package com.investlee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication(scanBasePackages = "com.investlee")
public class ApiMgnBatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiMgnBatchApplication.class, args);
    }
}