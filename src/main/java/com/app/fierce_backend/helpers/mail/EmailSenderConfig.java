package com.app.fierce_backend.helpers.mail;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Getter
@Setter
@Repository
@ConfigurationProperties(prefix = "config")
@PropertySource("classpath:email.yml")
public class EmailSenderConfig {

    private String supportEmail;
    private String password;
    private String host;
    private String smtpPort;
    private String sslEnable;
    private String authEnable;

    @PostConstruct
    public void checkConfig() {
        System.out.println("EMAIL_CONFIG {" + '\n' +
                "supportEmail='" + supportEmail + '\'' + '\n' +
                "password='" + password + '\'' + '\n' +
                "host='" + host + '\'' + '\n' +
                "smtpPort='" + smtpPort + '\'' + '\n' +
                "sslEnable='" + sslEnable + '\'' + '\n' +
                "authEnable='" + authEnable + '\'' +'\n' +
                '}');
    }
}
