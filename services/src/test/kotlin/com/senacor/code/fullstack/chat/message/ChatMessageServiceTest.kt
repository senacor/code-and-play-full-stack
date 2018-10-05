package com.senacor.code.fullstack.chat.message

import com.senacor.code.fullstack.chat.channel.Channel
import com.senacor.code.fullstack.chat.channel.ChannelNotFoundException
import com.senacor.code.fullstack.chat.channel.ChannelRepository
import com.senacor.code.fullstack.chat.channel.ChannelService
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ChatMessageServiceTest {

    private val repository = mockk<ChannelRepository>()

    private val channelService = ChannelService(repository)

    private val service = ChatMessageService(channelService)

    private val expectedChannels = listOf(
            Channel("general", "general"),
            Channel("dev", "dev"),
            Channel("humor", "humor"))

    @Before
    fun setup() {
        every { repository.findAll() } returns expectedChannels
    }

    @Test
    fun existsMessage() {
        every { repository.existsById("dev") } returns true

        val result = service.loadMessages("dev")

        assertEquals(mockMessages.filter { it.channelId == "dev" }, result)
    }

    @Test(expected = ChannelNotFoundException::class)
    fun existsMessageForNotExistingChannel() {
        every { repository.existsById("not-a-channel") } returns false

        service.loadMessages("not-a-channel")
    }

}