package com.senacor.code.fullstack.chat.rest;

import com.senacor.code.fullstack.chat.domain.Channel;
import com.senacor.code.fullstack.chat.service.ChannelService;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ChannelControllerTest {

    private ChannelService serviceMock = mock(ChannelService.class);

    private ChannelController controller = new ChannelController(serviceMock);

    @Test
    public void loadChannels() {
        List<Channel> expected = asList(
                new Channel("foo", "foo"),
                new Channel("foo", "bar"));
        when(serviceMock.loadChannels()).thenReturn(expected);

        List<Channel> result = controller.loadChannels();

        assertEquals(expected, result);
        verify(serviceMock).loadChannels();
    }


}
