package com.app.fierce_backend.domain.message.interfaces;

import com.app.fierce_backend.domain.message.Message;
import com.app.fierce_backend.domain.user.interfaces.UserDto;
import org.springframework.data.rest.core.config.Projection;

import javax.xml.crypto.Data;

@Projection(
        name = "MessageResponseDto",
        types = Message.class
)
public interface MessageResponseDto {
    UserDto getUser();
    String getData();
    String getDate();
}
