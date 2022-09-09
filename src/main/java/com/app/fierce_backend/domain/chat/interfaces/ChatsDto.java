package com.app.fierce_backend.domain.chat.interfaces;

import com.app.fierce_backend.domain.chat.Chat;
import org.springframework.data.rest.core.config.Projection;

import java.util.UUID;

@Projection(
        name = "ChatsDto",
        types = Chat.class
)
public interface ChatsDto {
    UUID getId();
    String getName();
}
