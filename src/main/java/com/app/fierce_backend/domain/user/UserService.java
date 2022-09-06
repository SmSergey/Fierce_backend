package com.app.fierce_backend.domain.user;

import com.app.fierce_backend.domain.user.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserService {

    @Value("${server.address}")
    private String serverAddress;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${server.port}")
    private String serverPort;

    public void performLogin(LoginRequest request) {

    }

    public void sendConfirmCode() {

    }

    public String generateConfirmEmailLink(String confirmCode) {
        return String.format("http://%s:%s%s/users/%s", serverAddress,serverPort ,contextPath, confirmCode);
    }

    @PostConstruct
    public void configCheck() {
        System.out.println("SERVER ADDERSS - " + this.serverAddress);
        System.out.println("context path - " + this.contextPath);
        System.out.println(generateConfirmEmailLink("dfsdfsdf"));
    }
}
