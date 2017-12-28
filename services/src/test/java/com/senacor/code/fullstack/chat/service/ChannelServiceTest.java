package com.senacor.code.fullstack.chat.service;

import com.senacor.code.fullstack.chat.domain.Channel;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ChannelServiceTest {

    private ChannelService service = new ChannelService();

    @Test
    public void fetchAllChannels() {
        List<Channel> expected = asList(
                new Channel("general"),
                new Channel("dev"),
                new Channel("humor"));

        List<Channel> result = service.loadChannels();

        assertEquals(expected, result);
    }

    @Test
    public void existsChannel() {
        boolean result = service.existsChannel("dev");

        assertTrue(result);
    }

    @Test
    public void existsChannelForNotExistingChannel() {
        boolean result = service.existsChannel("not-a-channel");

        assertFalse(result);
    }

}
