package com.app.fierce_backend.domain.user.dto;

import lombok.Data;

@Data
public class RegisterAccountRequest {

    private String email;
    private String password;
}
