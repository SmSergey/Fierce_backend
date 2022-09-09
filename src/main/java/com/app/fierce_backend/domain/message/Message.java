package com.app.fierce_backend.domain.message;

import com.app.fierce_backend.domain.chat.Chat;
import com.app.fierce_backend.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    private User user;

    private String data;

    @OneToOne
    private Chat chat;

    private Date date;
}
