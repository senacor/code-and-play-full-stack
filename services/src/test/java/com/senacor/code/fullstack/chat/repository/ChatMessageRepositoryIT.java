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
import static org.junit.Assert.*;

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

        ChatMessage messageTwo = repository.save(new ChatMessage("dev", "sender@test.de", "World!"));
        messageTwo.setCreationTimestamp(Instant.now());
        List<ChatMessage> result = repository.findAll();
        assertEquals(asList(messageOne, messageTwo), result);
    }

    @Test
    public void testFindByChannelIdOrderByCreationTimestampAsc() {
        assertTrue(repository.findAll().isEmpty());
        repository.save(new ChatMessage("general", "sender@test.de", "let's"));
        repository.save(new ChatMessage("dev", "sender@test.de", "Hello"));
        repository.save(new ChatMessage("general", "sender@test.de", "test"));
        repository.save(new ChatMessage("dev", "sender@test.de", "World!"));
        repository.save(new ChatMessage("general", "sender@test.de", "mongo"));

        List<ChatMessage> messages = repository.findByChannelIdOrderByCreationTimestampDesc("general");

        assertEquals(3, messages.size());
    }

}
