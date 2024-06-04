package com.dopamine.recycling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class RecyclingApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecyclingApplication.class, args);
    }

}
