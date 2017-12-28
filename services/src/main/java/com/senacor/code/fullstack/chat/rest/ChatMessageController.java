package com.senacor.code.fullstack.chat.rest;

import com.senacor.code.fullstack.chat.domain.ChatMessage;
import com.senacor.code.fullstack.chat.service.ChannelNotFoundException;
import com.senacor.code.fullstack.chat.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The REST controller for managing chat messages
 */
@RestController
@RequestMapping("/api/v1/{channel}/messages")
public class ChatMessageController {

    private ChatMessageService messageService;

    @Autowired
    public ChatMessageController(ChatMessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ChatMessage>> loadChatMessages(@PathVariable("channel") String channel) {
        try {
            List<ChatMessage> chatMessages = messageService.loadChatMessages(channel);
            return ResponseEntity.ok().body(chatMessages);
        } catch (ChannelNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
