package com.app.fierce_backend.domain.message;

import com.app.fierce_backend.domain.chat.Chat;
import com.app.fierce_backend.domain.chat.interfaces.ChatRepository;
import com.app.fierce_backend.domain.message.interfaces.MessageRepository;
import com.app.fierce_backend.domain.user.User;
import com.app.fierce_backend.domain.user.interfaces.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;


    @Transactional
    public void saveMessage(Long ownerId, String data, UUID chatId) {
        User user = userRepository.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("user with id %s wasn't found ", ownerId)));

        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("chat with id %s wasn't found ", chatId)));

        Message message = new Message();
        message.setData(data);
        message.setUser(user);
        message.setChat(chat);
        message.setDate(new Date());

        Optional.ofNullable(chat.getMessages())
                .orElseGet(ArrayList::new)
                .add(message);

        System.out.println(chat.getMessages());

        messageRepository.save(message);
    }
}
