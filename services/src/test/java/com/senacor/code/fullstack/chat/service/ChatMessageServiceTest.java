package com.senacor.code.fullstack.chat.service;

import com.senacor.code.fullstack.chat.domain.ChatMessage;
import com.senacor.code.fullstack.chat.repository.ChatMessageRepository;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        when(messageRepositoryMock.findByChannelIdOrderByCreationTimestampAsc("dev")).thenReturn(expectedList);

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

}
