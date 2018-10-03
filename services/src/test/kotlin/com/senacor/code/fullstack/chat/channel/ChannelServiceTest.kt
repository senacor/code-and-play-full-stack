package com.senacor.code.fullstack.chat.channel

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ChannelServiceTest {

    private val repository = mockk<ChannelRepository>()

    private val service = ChannelService(repository)

    private val expectedChannels = listOf(
        Channel("general", "general"),
        Channel("dev", "dev"),
        Channel("humor", "humor"))

    @Before
    fun setup() {
        every { repository.findAll() } returns expectedChannels
    }

    @Test
    fun fetchAllChannels() {
        val result = service.loadChannels()

        assertEquals(expectedChannels, result)
    }

    @Test
    fun existsChannel() {
        every { repository.existsById("dev") } returns true

        val result = service.existsChannel("dev")

        assertTrue(result)
    }

    @Test
    fun existsChannelForNotExistingChannel() {
        every { repository.existsById("not-a-channel") } returns false

        val result = service.existsChannel("not-a-channel")

        assertFalse(result)
    }

}