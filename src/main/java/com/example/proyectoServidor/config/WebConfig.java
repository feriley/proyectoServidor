package com.example.proyectoServidor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permite solicitudes desde http://localhost:4321 
        registry.addMapping("/api/**")  
                .allowedOrigins("http://localhost:4321") // Cambiado a la URL de tu front-end de Astro
                .allowedMethods("GET", "POST", "PUT", "DELETE"); // Métodos HTTP permitidos
    }
}