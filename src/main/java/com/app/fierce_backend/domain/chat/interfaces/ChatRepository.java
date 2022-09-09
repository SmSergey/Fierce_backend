package com.app.fierce_backend.domain.chat.interfaces;

import com.app.fierce_backend.domain.chat.Chat;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ChatRepository extends CrudRepository<Chat, UUID> {
}
