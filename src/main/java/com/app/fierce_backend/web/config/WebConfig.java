package com.app.fierce_backend.web.config;

import com.app.fierce_backend.helpers.portal.PortalConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final PortalConfig portalConfig;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "OPTIONS", "PUT", "PATCH", "DELETE")
//                .allowedOrigins(String.format("http://%s:%s", portalConfig.getAddress(), portalConfig.getPort()))
//                .allowCredentials(true)
                .allowedOrigins("*")
                .allowedHeaders("*");
    }
}
