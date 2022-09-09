package com.app.fierce_backend.domain.chat.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class CreateChatRequest {

    @NotBlank
    private String name;

    private List<String> usersId;
}
