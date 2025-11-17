package com.example.collabdocs.config;

import com.example.collabdocs.service.DocumentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedDocuments(DocumentService service) {
        return args -> {
            if (service.findAll().isEmpty()) {
                service.create("Document de exemplu", "Incepe sa editezi si vezi actualizarile in timp real.");
            }
        };
    }
}
