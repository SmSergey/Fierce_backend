package com.app.fierce_backend.domain.user;

import com.app.fierce_backend.domain.chat.Chat;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private Boolean isActivated = false;

    private String verificationCode;

    @ManyToMany
    private List<Chat> chats;

    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("id", id);
        object.put("email", email);
        object.put("firstName", firstName);
        return  object.toString();
    }
}
