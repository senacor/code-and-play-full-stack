package com.senacor.code.fullstack.chat;

import com.senacor.code.fullstack.chat.domain.Channel;
import com.senacor.code.fullstack.chat.domain.ChatMessage;
import com.senacor.code.fullstack.chat.repository.ChannelRepository;
import com.senacor.code.fullstack.chat.repository.ChatMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Class to fill our embedded Mongo DB with some test data.
 */
@Component
public class InitDatabaseOnStartupRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitDatabaseOnStartupRunner.class);

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Override
    public void run(String... args) throws Exception {
        // create some channels
        channelRepository.save(new Channel("general", "general"));
        channelRepository.save(new Channel("dev", "dev"));
        channelRepository.save(new Channel("humor", "humor"));

        // fetch all channels
        LOGGER.info("Channels in Mongo DB:");
        for (Channel channel : channelRepository.findAll()) {
            LOGGER.info("    " + channel);
        }

        // create some messages
        chatMessageRepository.save(new ChatMessage("general", "sender@test.de", "Hello"));
        chatMessageRepository.save(new ChatMessage("general", "sender@test.de", "World!"));
        chatMessageRepository.save(new ChatMessage("dev", "sender@test.de", "Did you ever tried Spring Boot 2.0.0?"));

        // fetch all channels
        LOGGER.info("ChatMessages in Mongo DB:");
        for (ChatMessage msg : chatMessageRepository.findAll()) {
            LOGGER.info("    " + msg);
        }

    }
}
