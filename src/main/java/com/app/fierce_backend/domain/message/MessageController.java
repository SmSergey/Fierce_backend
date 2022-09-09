package com.app.fierce_backend.domain.message;

import com.app.fierce_backend.domain.chat.Chat;
import com.app.fierce_backend.domain.chat.ChatService;
import com.app.fierce_backend.domain.chat.interfaces.ChatsDto;
import com.app.fierce_backend.domain.message.interfaces.MessageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final ChatService chatService;
    private final ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();


    @GetMapping("/messages/{chat_id}")
    public List<MessageResponseDto> getChatsMessages(@PathVariable("chat_id") UUID chatId) {
        return chatService.getChatMessages(chatId)
                .stream()
                .map(message -> projectionFactory.createProjection(MessageResponseDto.class, message)).toList();
    }
}
