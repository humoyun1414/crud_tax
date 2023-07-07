package com.example.demo_crud_project;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class DemoCrudProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoCrudProjectApplication.class, args);
    }

}
