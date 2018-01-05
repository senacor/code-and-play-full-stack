package com.senacor.code.fullstack.chat.service;

import com.senacor.code.fullstack.chat.domain.ChatMessage;
import com.senacor.code.fullstack.chat.repository.ChatMessageRepository;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ChatMessageServiceTest {

    private ChannelService channelServiceMock = mock(ChannelService.class);

    private ChatMessageRepository messageRepositoryMock = mock(ChatMessageRepository.class);

    private ChatMessageService service = new ChatMessageService(channelServiceMock, messageRepositoryMock);

    @Test
    public void fetchChatMessages() throws ChannelNotFoundException {
        when(channelServiceMock.existsChannel("dev")).thenReturn(true);
        List<ChatMessage> expectedList = Arrays.asList(
                new ChatMessage("dev", "s@t.de", "Hello"),
                new ChatMessage("dev", "s@t.de", "World!"));
        when(messageRepositoryMock.findByChannelIdOrderByCreationTimestampDesc("dev")).thenReturn(expectedList);

        List<ChatMessage> result = service.loadChatMessages("dev");

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
    public void saveChatMessage() throws ChannelNotFoundException {
        when(channelServiceMock.existsChannel("dev")).thenReturn(true);
        doAnswer(invocationOnMock -> {
            ChatMessage message = invocationOnMock.getArgument(0);
            message.setId("has-an-id");
            return message;
        }).when(messageRepositoryMock).save(any());

        ChatMessage savedMessage = service.saveChatMessage("dev", "sender@test.de", "Hello World!");

        verify(messageRepositoryMock).save(any());
        assertEquals("dev", savedMessage.getChannelId());
        assertEquals("sender@test.de", savedMessage.getSender());
        assertEquals("Hello World!", savedMessage.getMessage());
        assertEquals("has-an-id", savedMessage.getId());
        assertTrue(Duration.between(savedMessage.getCreationTimestamp(), Instant.now()).abs().getSeconds() < 3);
    }

    @Test(expected = ChannelNotFoundException.class)
    public void createChatMessagesThrowsExceptionIfChannelNotExist() throws ChannelNotFoundException {
        when(channelServiceMock.existsChannel("not-a-channel")).thenReturn(false);

        service.saveChatMessage("not-a-channel", "sender@test.de", "Hello World!");
    }

}
