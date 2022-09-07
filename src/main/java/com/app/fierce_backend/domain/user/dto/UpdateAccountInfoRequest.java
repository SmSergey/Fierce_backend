package com.app.fierce_backend.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class UpdateAccountInfoRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Min(6)
    private String password;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @JsonProperty("code")
    private String verificationCode;

}
