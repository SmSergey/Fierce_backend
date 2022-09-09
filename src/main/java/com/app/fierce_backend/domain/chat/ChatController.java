package com.app.fierce_backend.domain.chat;

import com.app.fierce_backend.common.ApiResponse;
import com.app.fierce_backend.domain.chat.dto.AttachUserToChatRequest;
import com.app.fierce_backend.domain.chat.interfaces.ChatsDto;
import com.app.fierce_backend.domain.chat.dto.CreateChatRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();

    @GetMapping("/chats")
    public List<ChatsDto> getUserChats() {
        return chatService.getUserChats()
                .stream()
                .map(chat -> projectionFactory.createProjection(ChatsDto.class, chat)).toList();
    }

    @PostMapping("/chats")
    public ResponseEntity<String> createChat(@RequestBody @Validated CreateChatRequest request) {
        Chat createdChat = chatService.createNewChat(request.getName(), request.getUsersId());

        return new ApiResponse()
                .setStatus(HttpStatus.CREATED.value())
                .setMessage("created")
                .addField("chat_id", createdChat.getId()).build();
    }

    @PostMapping("/chats/attach")
    public ResponseEntity<String> attachUserToChat(@RequestBody @Validated AttachUserToChatRequest request) {
        chatService.attachUserToChat(request.getUserId(), request.getChatId());
        return new ApiResponse()
                .setStatus(200)
                .setMessage("Added").build();
    }
}
