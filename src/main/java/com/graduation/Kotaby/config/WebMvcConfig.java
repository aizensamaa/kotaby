package com.graduation.Kotaby.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Allow all origins
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS"); // Allow specific HTTP methods
//                .allowedHeaders("*") // Allow all headers
//                .allowCredentials(true); // Allow credentials
    }
}
