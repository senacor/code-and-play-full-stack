package com.senacor.code.fullstack.chat;

import com.senacor.code.fullstack.chat.domain.Channel;
import com.senacor.code.fullstack.chat.repository.ChannelRepository;
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
    private ChannelRepository repository;

    @Override
    public void run(String... args) throws Exception {
        // create some channels
        repository.save(new Channel("general", "general"));
        repository.save(new Channel("dev", "dev"));
        repository.save(new Channel("humor", "humor"));

        // fetch all channels
        LOGGER.info("Channels in Mongo DB:");
        for (Channel msg : repository.findAll()) {
            LOGGER.info("    " + msg);
        }
    }
}
