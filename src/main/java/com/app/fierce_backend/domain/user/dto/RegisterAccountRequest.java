package com.app.fierce_backend.domain.user.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegisterAccountRequest {

    @NotBlank
    @Email
    private String email;
}
