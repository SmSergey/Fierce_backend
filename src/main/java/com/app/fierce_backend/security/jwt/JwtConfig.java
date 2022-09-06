package com.app.fierce_backend.security.jwt;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Data
@Getter
@Repository
@ConfigurationProperties(prefix = "jwt")
@PropertySource("classpath:jwt.yml")
public class JwtConfig {
    private String header;
    private String secret;

    private Long accessExpiration;
    private Long refreshExpiration;

    @PostConstruct
    public void checkConfig() {
        System.out.println("JWT CONFIG {" + '\n' +
                "header='" + getHeader() + '\'' + '\n' +
                "secret='" + getSecret() + '\'' + '\n' +
                "accessExpiration='" + getAccessExpiration() + '\'' + '\n' +
                "refreshExpiration='" + getRefreshExpiration() + '\'' + '\n' +
                '}');
    }
}
