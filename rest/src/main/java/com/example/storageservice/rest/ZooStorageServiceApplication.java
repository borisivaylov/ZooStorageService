package com.example.storageservice.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.example.storageservice")
@EntityScan(basePackages = "com.example.storageservice.persistence.entity")
@EnableJpaRepositories(basePackages = "com.example.storageservice.persistence.repository")

@SpringBootApplication
public class
ZooStorageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZooStorageServiceApplication.class, args);
    }

}
