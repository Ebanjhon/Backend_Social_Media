package com.eban.social_media.Configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Áp dụng cho tất cả các endpoint
                .allowedOrigins("*") // Cho phép tất cả các nguồn gốc
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Các method cho phép
                .allowedHeaders("*") // Cho phép tất cả header
                .allowCredentials(false); // Không sử dụng credentials
    }
}
