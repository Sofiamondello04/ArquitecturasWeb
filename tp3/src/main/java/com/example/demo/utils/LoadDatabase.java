package com.example.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.model.Carrera;
import com.example.demo.model.Estudiante;
import com.example.demo.repository.CarreraRepository;
import com.example.demo.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner initDatabase(@Qualifier("estudianteRepository") EstudianteRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Estudiante("Bernardo", "Gonzalez", 32, "Masculino", "Tandil", (int) 35418667, null)));
            log.info("Preloading " + repository.save(new Estudiante("Sofia", "Mondelo", 28, "Femenino", "Tandil", (int) 38418467, null)));
            log.info("Preloading " + repository.save(new Estudiante("Mauro", "Valerioti", 29, "NoBinario", "Tandil", (int) 38602821, null)));
        };
    }
    
    @Bean
    CommandLineRunner initDatabase2(@Qualifier("carreraRepository") CarreraRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Carrera("tudai", (int) 3)));
            log.info("Preloading " + repository.save(new Carrera("ing sistemas", (int) 5)));
        };
    }

}