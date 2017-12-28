package com.senacor.code.fullstack.chat.service;

import com.senacor.code.fullstack.chat.domain.Channel;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

/**
 * A service to manage communication channels.
 */
@Service
public class ChannelService {

    private static final List<Channel> ALL_CHANNELS = unmodifiableList(asList(
            new Channel("general"),
            new Channel("dev"),
            new Channel("humor")
    ));

    public List<Channel> loadChannels() {
        return ALL_CHANNELS;
    }

    public boolean existsChannel(String name) {
        return ALL_CHANNELS.contains(new Channel(name));
    }
}
