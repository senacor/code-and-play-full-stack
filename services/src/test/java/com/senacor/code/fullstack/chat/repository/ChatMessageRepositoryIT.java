package com.senacor.code.fullstack.chat.repository;

import com.senacor.code.fullstack.chat.ChatApplication;
import com.senacor.code.fullstack.chat.domain.ChatMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.Instant;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ChatApplication.class)
public class ChatMessageRepositoryIT {

    @Autowired
    private ChatMessageRepository repository;

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
    public void saveAndLoadChatMessages() {
        assertTrue(repository.findAll().isEmpty());

        ChatMessage messageOne = repository.save(new ChatMessage("dev", "sender@test.de", "Hello"));
        messageOne.setCreationTimestamp(Instant.now().minusSeconds(20));
        assertEquals(1, repository.findAll().size());

        ChatMessage messageTwo = repository.save(new ChatMessage("dev", "sender@test.de","World!"));
                messageTwo.setCreationTimestamp(Instant.now());


        List<ChatMessage> result = repository.findAll();
        assertEquals(2, result.size());

        assertEquals(messageOne.getChannel(), result.get(0).getChannel());
        assertEquals(messageOne.getSender(), result.get(0).getSender());
        assertEquals(messageOne.getMessage(), result.get(0).getMessage());

        assertEquals(messageTwo.getChannel(), result.get(1).getChannel());
        assertEquals(messageTwo.getSender(), result.get(1).getSender());
        assertEquals(messageTwo.getMessage(), result.get(1).getMessage());
    }

    @Test
    public void findAllByChannelOrderByCreationTimestampAsc() {
        repository.save(new ChatMessage("c1", "s@t.d", "m1").setCreationTimestamp(Instant.now().minusSeconds(60)));
        repository.save(new ChatMessage("c1", "s@t.d", "m2").setCreationTimestamp(Instant.now().minusSeconds(30)));
        repository.save(new ChatMessage("c2", "s@t.d", "m3").setCreationTimestamp(Instant.now().minusSeconds(30)));
        repository.save(new ChatMessage("c2", "s@t.d", "m4").setCreationTimestamp(Instant.now().minusSeconds(60)));
        repository.save(new ChatMessage("c3", "s@t.d", "m5").setCreationTimestamp(Instant.now()));

        List<ChatMessage> channelOne = repository.findAllByChannelOrderByCreationTimestampAsc("c1");
        assertEquals(2, channelOne.size());
        assertEquals("m1", channelOne.get(0).getMessage());
        assertEquals("m2", channelOne.get(1).getMessage());

        List<ChatMessage> channelTwo = repository.findAllByChannelOrderByCreationTimestampAsc("c2");
        assertEquals(2, channelTwo.size());
        assertEquals("m4", channelTwo.get(0).getMessage());
        assertEquals("m3", channelTwo.get(1).getMessage());

        List<ChatMessage> channelThree = repository.findAllByChannelOrderByCreationTimestampAsc("c3");
        assertEquals(1, channelThree.size());
        assertEquals("m5", channelThree.get(0).getMessage());

        List<ChatMessage> channelFour = repository.findAllByChannelOrderByCreationTimestampAsc("c4");
        assertEquals(0, channelFour.size());
    }

}
