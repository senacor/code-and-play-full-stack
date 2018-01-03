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

    private ChatMessageRepository repository;

    @Autowired
    public ChatMessageService(ChannelService channelService, ChatMessageRepository repository) {
        this.channelService = channelService;
        this.repository = repository;
    }

    public List<ChatMessage> loadChatMessages(String channelName) throws ChannelNotFoundException {
        if (!channelService.existsChannel(channelName)) {
            throw new ChannelNotFoundException();
        }
        return repository.findAllByChannelOrderByCreationTimestampAsc(channelName);
    }
}
