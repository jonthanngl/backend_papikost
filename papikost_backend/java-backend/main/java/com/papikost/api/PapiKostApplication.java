package com.papikost.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PapiKostApplication {
    public static void main(String[] args) {
        SpringApplication.run(PapiKostApplication.class, args);
        System.out.println("PapiKost Spring Boot Rest API is running on http://localhost:8080");
    }
}
