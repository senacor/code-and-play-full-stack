package com.senacor.code.fullstack.chat.rest;

import com.senacor.code.fullstack.chat.domain.ChatMessage;
import com.senacor.code.fullstack.chat.service.ChannelNotFoundException;
import com.senacor.code.fullstack.chat.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * The REST controller for managing chat messages
 */
@RestController
@RequestMapping(ChatMessageController.CHAT_MESSAGES_PATH)
public class ChatMessageController {

    static final String CHAT_MESSAGES_PATH = "/api/v1/{channel}/messages";

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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> newChatMessages(@PathVariable("channel") String channel, @RequestBody ChatMessage chatMessage) {
        try {
            ChatMessage newChatMessage = messageService.createChatMessage(channel, chatMessage.getSender(), chatMessage.getMessage());

            UriComponents location = UriComponentsBuilder.newInstance().path(CHAT_MESSAGES_PATH).pathSegment(newChatMessage.getId()).buildAndExpand(channel);
            return ResponseEntity.created(location.toUri()).build();

        } catch (ChannelNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
