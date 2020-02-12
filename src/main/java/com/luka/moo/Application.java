package com.luka.moo;

import com.luka.moo.services.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        System.out.println(dtf.format(now) + " - Moo main ran");

        SpringApplication.run(Application.class, args);
        CustomerService.initDummyData();
        now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        System.out.println("Moo started");
    }
}
