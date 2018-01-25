package com.senacor.code.fullstack.chat.service;

import com.senacor.code.fullstack.chat.domain.ChatMessage;
import com.senacor.code.fullstack.chat.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing chat messages.
 */
@Service
public class ChatMessageService {

    private ChannelService channelService;

    private ChatMessageRepository messageRepository;

    @Autowired
    public ChatMessageService(ChannelService channelService, ChatMessageRepository messageRepository) {
        this.channelService = channelService;
        this.messageRepository = messageRepository;
    }

    public List<ChatMessage> loadChatMessages(String channelId) {
        if (!channelService.existsChannel(channelId)) {
            throw new ChannelNotFoundException();
        }
        return messageRepository.findByChannelIdOrderByCreationTimestampAsc(channelId);
    }
}
