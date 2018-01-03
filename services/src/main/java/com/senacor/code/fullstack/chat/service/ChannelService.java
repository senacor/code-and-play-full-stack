package com.senacor.code.fullstack.chat.service;

import com.senacor.code.fullstack.chat.domain.Channel;
import com.senacor.code.fullstack.chat.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

/**
 * A service to manage communication channels.
 */
@Service
public class ChannelService {

    private ChannelRepository channelRepository;

    @Autowired
    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    public List<Channel> loadChannels() {
        return channelRepository.findAll();
    }

    public boolean existsChannel(String channelId) {
        return channelRepository.existsById(channelId);
    }
}
