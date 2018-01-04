package com.senacor.code.fullstack.chat.service;

import com.senacor.code.fullstack.chat.domain.ChatMessage;
import com.senacor.code.fullstack.chat.repository.ChatMessageRepository;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ChatMessageServiceTest {

    private ChannelService channelServiceMock = mock(ChannelService.class);
    private ChatMessageRepository repository = mock(ChatMessageRepository.class);

    private ChatMessageService service = new ChatMessageService(channelServiceMock, repository);

    @Test
    public void fetchChatMessages() throws ChannelNotFoundException {
        String channel = "dev";
        List<ChatMessage> expected = asList(
                new ChatMessage(channel, "sender@test.de", "Hello"),
                new ChatMessage(channel, "sender@test.de", "World!"));
        when(channelServiceMock.existsChannel(channel)).thenReturn(true);
        when(repository.findAllByChannelOrderByCreationTimestampAsc(channel)).thenReturn(expected);

        List<ChatMessage> result = service.loadChatMessages(channel);

        assertEquals(2, result.size());
        assertEquals("Hello", result.get(0).getMessage());
        assertEquals("World!", result.get(1).getMessage());
    }

    @Test(expected = ChannelNotFoundException.class)
    public void loadChatMessagesThrowsExceptionIfChannelNotExist() throws ChannelNotFoundException {
        when(channelServiceMock.existsChannel("not-a-channel")).thenReturn(false);

        service.loadChatMessages("not-a-channel");
    }

    @Test
    public void createChatMessage() throws ChannelNotFoundException {
        when(channelServiceMock.existsChannel("dev")).thenReturn(true);
        doAnswer(invocationOnMock -> {
            ChatMessage message = invocationOnMock.getArgument(0);
            message.setId("has-an-id");
            return message;
        }).when(repository).save(any());

        ChatMessage savedMessage = service.createChatMessage("dev", "sender@test.de", "Hello World!");

        verify(repository).save(any());
        assertEquals("dev", savedMessage.getChannel());
        assertEquals("sender@test.de", savedMessage.getSender());
        assertEquals("Hello World!", savedMessage.getMessage());
        assertEquals("has-an-id", savedMessage.getId());
        assertTrue(Duration.between(savedMessage.getCreationTimestamp(), Instant.now()).abs().getSeconds() < 3);
    }


    @Test(expected = ChannelNotFoundException.class)
    public void createChatMessagesThrowsExceptionIfChannelNotExist() throws ChannelNotFoundException {
        when(channelServiceMock.existsChannel("not-a-channel")).thenReturn(false);

        service.createChatMessage("not-a-channel", "sender@test.de", "Hello World!");
    }
}
