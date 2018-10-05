package com.senacor.code.fullstack.chat.message

import com.senacor.code.fullstack.chat.channel.Channel
import com.senacor.code.fullstack.chat.channel.ChannelNotFoundException
import com.senacor.code.fullstack.chat.channel.ChannelRepository
import com.senacor.code.fullstack.chat.channel.ChannelService
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ChatMessageServiceTest {

    private val repository = mockk<ChannelRepository>()

    private val service = ChannelService(repository)

    private val expectedChannels = listOf(
            Channel("general", "general"),
            Channel("dev", "dev"),
            Channel("humor", "humor"))

    private val chatMessageRepository = mockk<ChatMessageRepository>()

    private val chatMessageService = ChatMessageService(service, chatMessageRepository)

    private val expectedMessages = listOf(
                    ChatMessage("dev", "julia@test.de", "Hello"),
                    ChatMessage("dev", "julia@test.de", "World!"),
                    ChatMessage("general", "max@test.de", "Have fun!"),
                    ChatMessage("general", "tcuje@freenet.de", "Hello my name is Thomas")
                )

    @Before
    fun setup() {
        every { repository.findAll() } returns expectedChannels
        every { chatMessageRepository.findAll() } returns expectedMessages
    }

    @Test
    fun existsMessage() {
        every { repository.existsById("dev") } returns true

        val result = chatMessageService.loadMessages("dev")

        assertEquals(chatMessageRepository.findAll().filter { it.channelId == "dev" }, result)
    }

    @Test(expected = ChannelNotFoundException::class)
    fun existsMessageForNotExistingChannel() {
        every { repository.existsById("not-a-channel") } returns false

        chatMessageService.loadMessages("not-a-channel")
    }

    @Test
    fun createMessage() {
        every { repository.existsById("dev") } returns true
        every { chatMessageRepository.save(any<ChatMessage>()) } answers { this.value }

        val channelId = "dev"
        val sender = "tcuje@freenet.de"
        val message = "This is it!"
        val result = chatMessageService.createMessage(channelId, sender, message)

        assertEquals(channelId, result.channelId)
        assertEquals(sender, result.sender)
        assertEquals(message, result.message)
    }

    @Test(expected = ChannelNotFoundException::class)
    fun createMessageWithNotExistingChannel() {
        every { repository.existsById("not-a-channel") } returns false

        chatMessageService.createMessage("not-a-channel", "tcuje@freenet.de", "This is it!")
    }

}