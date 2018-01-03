package com.senacor.code.fullstack.chat.service;

import com.senacor.code.fullstack.chat.domain.ChatMessage;
import com.senacor.code.fullstack.chat.repository.ChatMessageRepository;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

}
