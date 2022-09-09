package com.app.fierce_backend.domain.chat;

import com.app.fierce_backend.domain.message.Message;
import com.app.fierce_backend.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "chats")
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @OneToMany
    private List<User> users;

    @OneToMany
    private List<Message> messages;

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
