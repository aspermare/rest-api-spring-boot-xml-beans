/*
 * Copyright (c) 2020 KU (TEST statement for qulice style check)
 */
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * REST API application running by Spring-boot.
 * @since 1.0
 */
@SpringBootApplication
public class RestApiVer3Application {

    /**
     * Main method - execution of application.
     *   @param args Arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(RestApiVer3Application.class, args);
    }

}
