package com.senacor.code.fullstack.chat.service;

import com.senacor.code.fullstack.chat.domain.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

/**
 * Service for managing chat messages.
 */
@Service
public class ChatMessageService {

    private static final List<ChatMessage> ALL_MESSAGES = unmodifiableList(asList(
            new ChatMessage("dev", "sender@test.de", "Hello"),
            new ChatMessage("dev", "sender@test.de", "World!")
    ));

    public List<ChatMessage> loadChatMessages(String channelId) {
        return ALL_MESSAGES;
    }
}
