package com.chat.support;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final ChatMessageRepository chatMessageRepository;

    public ChatController(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        chatMessageRepository.save(chatMessage);
        System.out.println("Mensagem Salva no Banco: " + chatMessage);
        return chatMessage;
    }
}