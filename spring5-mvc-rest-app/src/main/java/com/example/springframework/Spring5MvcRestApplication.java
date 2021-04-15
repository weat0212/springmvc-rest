package com.example.springframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class Spring5MvcRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(Spring5MvcRestApplication.class, args);
    }

}
