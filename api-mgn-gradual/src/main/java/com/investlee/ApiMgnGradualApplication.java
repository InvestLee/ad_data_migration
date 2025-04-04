package com.investlee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.investlee")
public class ApiMgnGradualApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiMgnGradualApplication.class, args);
    }
}
