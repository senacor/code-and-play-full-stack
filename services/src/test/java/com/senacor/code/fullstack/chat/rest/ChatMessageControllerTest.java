package com.senacor.code.fullstack.chat.rest;

import com.senacor.code.fullstack.chat.domain.ChatMessage;
import com.senacor.code.fullstack.chat.service.ChannelNotFoundException;
import com.senacor.code.fullstack.chat.service.ChatMessageService;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

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
    public void loadChannelMessages() throws ChannelNotFoundException {
        List<ChatMessage> expected = asList(new ChatMessage(), new ChatMessage());
        when(serviceMock.loadChatMessages("dev")).thenReturn(expected);

        ResponseEntity<List<ChatMessage>> result = controller.loadChatMessages("dev");

        assertEquals(ResponseEntity.ok().body(expected), result);
        verify(serviceMock).loadChatMessages("dev");
    }

}
