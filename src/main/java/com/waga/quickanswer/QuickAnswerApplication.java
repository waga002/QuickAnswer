package com.waga.quickanswer;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class QuickAnswerApplication {
    private static Logger log = Logger.getLogger(String.valueOf(QuickAnswerApplication.class));

    public static void main(String[] args) {
        SpringApplication.run(QuickAnswerApplication.class, args);

        System.out.println("hello world");
        log.info("This is log4j log.");
    }

}
