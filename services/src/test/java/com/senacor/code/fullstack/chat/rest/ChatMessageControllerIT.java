package com.senacor.code.fullstack.chat.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senacor.code.fullstack.chat.domain.ChatMessage;
import com.senacor.code.fullstack.chat.repository.ChatMessageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChatMessageControllerIT {

    private MockMvc mockMvc;

    @Autowired
    private ChatMessageRepository repository;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void loadMessages() throws Exception {
        String channel = "dev";
        repository.save(new ChatMessage(channel, "sender@test.de", "Hello"));
        repository.save(new ChatMessage(channel, "sender@test.de", "World!"));

        mockMvc.perform(get("/api/v1/channels/{channel}/messages", channel)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].message").value("World!"))
                .andExpect(jsonPath("$[1].message").value("Hello"));
    }

    @Test
    public void loadMessagesForNotExistingChannel() throws Exception {
        String channel = "not-a-channel";
        mockMvc.perform(get("/api/v1/{channel}/messages", channel)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isNotFound());
    }

    @Test
    public void newMessages() throws Exception {
        String channel = "dev";
        ChatMessage newMessage = new ChatMessage(null, "xy@test.de", "My first message");

        mockMvc.perform(post("/api/v1/channels/{channel}/messages", channel)
                .content(objectMapper.writeValueAsString(newMessage))
                .contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", startsWith("/api/v1/channels/dev/messages/")))
                .andDo(result -> {
                    // Check if the new message can be found in the database
                    String location = result.getResponse().getHeader("location");
                    String newMessageId = location.substring(location.lastIndexOf("/") + 1);
                    ChatMessage savedMessage = repository.findById(newMessageId).get();
                    assertEquals("xy@test.de", savedMessage.getSender());
                    assertEquals("My first message", savedMessage.getMessage());
                });
    }
}
