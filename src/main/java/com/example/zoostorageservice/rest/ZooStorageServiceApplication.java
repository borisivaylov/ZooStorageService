package com.example.zoostorageservice.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.example.zoostorageservice")
@EntityScan(basePackages = "com.example.zoostorageservice.persistance.entity")
@EnableJpaRepositories(basePackages = "com.example.zoostorageservice.persistance.repository")
@SpringBootApplication
public class ZooStorageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZooStorageServiceApplication.class, args);
    }

}
