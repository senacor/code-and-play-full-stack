package com.senacor.code.fullstack.chat.service;

import com.senacor.code.fullstack.chat.domain.Channel;
import com.senacor.code.fullstack.chat.repository.ChannelRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ChannelServiceTest {

    private ChannelRepository repository = mock(ChannelRepository.class);

    private ChannelService service = new ChannelService(repository);

    @Before
    public void setup() {
        when(repository.findAll()).thenReturn(asList(
                new Channel("general", "general"),
                new Channel("dev", "dev"),
                new Channel("humor", "humor")));
    }

    @Test
    public void fetchAllChannels() {
        List<Channel> expected = asList(
                new Channel("general", "general"),
                new Channel("dev", "dev"),
                new Channel("humor", "humor"));

        List<Channel> result = service.loadChannels();

        assertEquals(expected, result);
    }

    @Test
    public void existsChannel() {
        when(repository.existsById("dev")).thenReturn(true);

        boolean result = service.existsChannel("dev");

        assertTrue(result);
    }

    @Test
    public void existsChannelForNotExistingChannel() {
        when(repository.existsById("not-a-channel")).thenReturn(false);

        boolean result = service.existsChannel("not-a-channel");

        assertFalse(result);
    }

}
