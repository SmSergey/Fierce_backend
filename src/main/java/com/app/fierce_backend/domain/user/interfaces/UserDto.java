package com.app.fierce_backend.domain.user.interfaces;

import com.app.fierce_backend.domain.user.User;
import org.springframework.data.rest.core.config.Projection;

@Projection(
        name = "UserDto",
        types = User.class
)
public interface UserDto {
    Long getId();
    String getFirstName();
    String getLastName();
}
