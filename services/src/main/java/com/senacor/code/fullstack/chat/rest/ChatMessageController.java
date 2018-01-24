package com.senacor.code.fullstack.chat.rest;

import com.senacor.code.fullstack.chat.domain.ChatMessage;
import com.senacor.code.fullstack.chat.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The REST controller for managing chat messages.
 */
@RestController
@RequestMapping("/api/v1/channels/{channel}/messages")
public class ChatMessageController {

    private ChatMessageService messageService;

    @Autowired
    public ChatMessageController(ChatMessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<ChatMessage> loadChatMessages(@PathVariable("channel") String channel) {
        return messageService.loadChatMessages(channel);
    }
}
