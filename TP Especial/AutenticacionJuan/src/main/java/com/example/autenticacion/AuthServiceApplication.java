package com.example.autenticacion;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.autenticacion.config.SecurityConfiguration;

import java.io.IOException;

@SpringBootApplication
public class AuthServiceApplication {
    @Autowired
    SecurityConfiguration securityConfiguration;
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

    @PostConstruct
    public void init() throws IOException {
        securityConfiguration.databasePopulator();
    }
}
