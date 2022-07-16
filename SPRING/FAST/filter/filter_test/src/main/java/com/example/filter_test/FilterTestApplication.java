package com.example.filter_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class FilterTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilterTestApplication.class, args);
    }

}
