package com.app.fierce_backend.domain.message.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class MessageDto {
    private String data;
    private Long ownerId;
    private UUID chatId;
}
