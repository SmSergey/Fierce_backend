package com.app.fierce_backend;

import com.app.fierce_backend.domain.chat.Chat;
import com.app.fierce_backend.domain.chat.interfaces.ChatRepository;
import com.app.fierce_backend.domain.user.User;
import com.app.fierce_backend.domain.user.interfaces.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@RequiredArgsConstructor
public class FierceBackendApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    public static void main(String[] args) {
        SpringApplication.run(FierceBackendApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("test");
    }
}
