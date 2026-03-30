package com.smartstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartStudyApplication.class, args);
    }
}
