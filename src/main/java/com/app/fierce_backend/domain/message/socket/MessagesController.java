package com.app.fierce_backend.domain.message.socket;

import com.app.fierce_backend.domain.message.MessageService;
import com.app.fierce_backend.domain.message.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessagesController {

    private final MessageService messageService;

    @MessageMapping("/message")
    @SendTo("/topic/test")
    public String testMessage(MessageDto msg) {
        messageService.saveMessage(msg.getOwnerId(), msg.getData(), msg.getChatId());
        return "saved";
    }
}
