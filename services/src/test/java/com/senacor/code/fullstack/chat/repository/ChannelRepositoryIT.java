package com.senacor.code.fullstack.chat.repository;

import com.senacor.code.fullstack.chat.ChatApplication;
import com.senacor.code.fullstack.chat.domain.Channel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ChatApplication.class)
public class ChannelRepositoryIT {

    @Autowired
    private ChannelRepository repository;

    @Before
    public void setup() {
        // ensure we are staring without any chat messages
        repository.deleteAll();
    }

    @After
    public void cleanup() {
        repository.deleteAll();
    }

    @Test
    public void saveAndLoadChannels() {
        assertTrue(repository.findAll().isEmpty());

        Channel channelOne = repository.save(new Channel("dev-id", "dev-name"));
        assertEquals(1, repository.findAll().size());

        Channel channelTwo = repository.save(new Channel("general-id", "general-name"));

        assertEquals(asList(channelOne, channelTwo), repository.findAll());
    }

}
