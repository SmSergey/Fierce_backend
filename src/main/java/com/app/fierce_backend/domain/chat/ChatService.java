package com.app.fierce_backend.domain.chat;

import com.app.fierce_backend.domain.chat.interfaces.ChatRepository;
import com.app.fierce_backend.domain.message.Message;
import com.app.fierce_backend.domain.user.User;
import com.app.fierce_backend.domain.user.interfaces.UserRepository;
import com.app.fierce_backend.security.services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    @Transactional
    public Chat createNewChat(String name, List<String> usersId) {
        Chat chat = new Chat();
        chat.setName(name);

        List<User> usersInChat = new ArrayList<>();
        usersId.forEach(userId -> {
            User user = userRepository.findById(Long.valueOf(userId))
                    .orElseThrow(() -> new EntityNotFoundException(String.format("User with this id %s wasn't found ", userId)));
            usersInChat.add(user);
            user.getChats().add(chat);
        });
        chat.setUsers(usersInChat);
        chatRepository.save(chat);
        return chat;
    }

    public List<Chat> getUserChats() {
        User user = userRepository.findById(21L).orElseGet(User::new);
        return Optional.ofNullable(user.getChats()).orElseGet(ArrayList::new);
    }

    @Transactional
    public List<Message> getChatMessages(UUID chatId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("chat with id %s wasn't found", chatId)));

        return Optional.ofNullable(chat.getMessages()).orElseGet(ArrayList::new);
    }


    public void attachUserToChat(String userId, UUID chatId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Chat with id %s wasn't found ", chatId)));
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id %s wasn't found", userId)));

        Optional.ofNullable(chat.getUsers()).orElseGet(ArrayList::new)
                .add(user);

        chatRepository.save(chat);
    }
}
