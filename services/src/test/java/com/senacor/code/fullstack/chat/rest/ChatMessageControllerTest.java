package com.senacor.code.fullstack.chat.rest;

import com.senacor.code.fullstack.chat.domain.ChatMessage;
import com.senacor.code.fullstack.chat.service.ChatMessageService;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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

    @Test
    public void newChatMessages() throws URISyntaxException {
        ChatMessage savedMessage = new ChatMessage("dev", "sender@test.de", "Hello World!");
        savedMessage.setId("id-of-msg");
        //when(serviceMock.createChatMessage("dev", "sender@test.de", "Hello Word!")).thenReturn(savedMessage);
        when(serviceMock.saveChatMessage(any(), any(), any())).thenReturn(savedMessage);

        ResponseEntity<Void> result = controller.newChatMessages("dev", new ChatMessage(null, "sender@test.de", "Hello World!"));

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(new URI("/api/v1/channels/dev/messages/id-of-msg") , result.getHeaders().getLocation());
        verify(serviceMock).saveChatMessage("dev", "sender@test.de", "Hello World!");
    }
}
