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
            log.info("Preloading " + repository.save(new Estudiante((int) 1234,"Bernardo", "Gonzalez", 32, "Masculino", "Tandil", (int) 35418667, null)));
            log.info("Preloading " + repository.save(new Estudiante((int) 12345,"Sofia", "Mondelo", 28, "Femenino", "Tandil", (int) 38418467, null)));
        };
    }
    
    @Bean
    CommandLineRunner initDatabase2(@Qualifier("carreraRepository") CarreraRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Carrera((int) 2, "tudai", (int) 5)));
        };
    }

}