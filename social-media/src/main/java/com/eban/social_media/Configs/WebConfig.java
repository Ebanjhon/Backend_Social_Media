package com.eban.social_media.Configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:3000") // Cho phép origin của React app
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Bổ sung OPTIONS
                .allowedHeaders("*")
                .allowCredentials(false); // Cho phép thông tin đăng nhập (nếu cần)
    }
}


