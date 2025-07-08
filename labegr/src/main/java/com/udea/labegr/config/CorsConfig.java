package com.udea.labegr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/graphql")
                .allowedOrigins("http://localhost:5500", "http://127.0.0.1:5500") // Cambia según el puerto del frontend
                .allowedMethods("POST", "GET", "OPTIONS")
                .allowedHeaders("*");
    }
}
