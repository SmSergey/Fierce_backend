package com.app.fierce_backend.domain.chat.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class AttachUserToChatRequest {
    @NotBlank
    private String userId;

    @NotBlank
    private UUID chatId;
}
