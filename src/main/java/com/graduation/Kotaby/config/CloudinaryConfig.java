package com.graduation.Kotaby.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap("cloud_name", "dwxhk8zlh", "api_key", "977114935221344", "api_secret", "mqW8ADFlzAM8GJC1oJSZ5ugRbsE"));
    }

}
