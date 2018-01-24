package com.senacor.code.fullstack.chat.rest;

import com.senacor.code.fullstack.chat.domain.ChatMessage;
import com.senacor.code.fullstack.chat.service.ChatMessageService;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ChatMessageControllerTest {

    private ChatMessageService serviceMock = mock(ChatMessageService.class);

    private ChatMessageController controller = new ChatMessageController(serviceMock);

    @Test
    public void loadChannelMessages() {
        List<ChatMessage> expected = asList(new ChatMessage(), new ChatMessage());
        when(serviceMock.loadChatMessages("dev")).thenReturn(expected);

        List<ChatMessage> result = controller.loadChatMessages("dev");

        assertEquals(expected, result);
        verify(serviceMock).loadChatMessages("dev");
    }

}
