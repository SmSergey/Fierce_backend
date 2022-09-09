package com.app.fierce_backend.security.jwt;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class JwtConfigTest {


    @Autowired
    private JwtConfig jwtConfig;


    @Test
    void getSecret() {
        String secret = jwtConfig.getSecret();
    }
}