package com.codemagician.onlinelibrary.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/6/18 13:48
 *
 * CORS configuration
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // @TODO to be tested
        registry.addMapping("/api/**").allowedOrigins("http://localhost:3000");
    }

}
