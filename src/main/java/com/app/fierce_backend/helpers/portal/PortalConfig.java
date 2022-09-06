package com.app.fierce_backend.helpers.portal;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Getter
@Setter
@Repository
@ConfigurationProperties(prefix = "portal")
@PropertySource("classpath:portal.yml")
public class PortalConfig {
    private String address;
    private String port;


    @PostConstruct
    public void test() {
        System.out.println("Portal Config {" + "\n" +
                "address = " + address + "\n" +
                "port = " + port + "\n" +
                "}");
    }
}
