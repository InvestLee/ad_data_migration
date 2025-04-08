package com.investlee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.investlee")
public class ApiMgnInternalApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiMgnInternalApplication.class, args);
    }
}
