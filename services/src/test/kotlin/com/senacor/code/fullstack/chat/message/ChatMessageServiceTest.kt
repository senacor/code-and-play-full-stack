package com.senacor.code.fullstack.chat.message

import com.senacor.code.fullstack.chat.channel.ChannelService
import org.junit.Assert.assertEquals
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.Duration
import java.time.Instant

class ChatMessageServiceTest {
    private val channelServiceMock = mockk<ChannelService>()
    private val chatMessageRepository = mockk<ChatMessageRepository>()

    private val service = ChatMessageService(channelServiceMock, chatMessageRepository)

    @Test
    fun fetchChatMessages() {
        every { channelServiceMock.existsChannel("dev") } returns true
        val expectedList = listOf(
            ChatMessage("dev", "s@t.de", "Hello"),
            ChatMessage("dev", "s@t.de", "World!")
        )

        every { chatMessageRepository.findByChannelIdOrderByCreationTimestampDesc("dev") } returns expectedList

        val result = service.loadChatMessages("dev")

        assertEquals(2, result.size)
        assertEquals("Hello", result[0].message)
        assertEquals("World!", result[1].message)
    }

    @Test(expected = ChannelNotFoundException::class)
    @Throws(ChannelNotFoundException::class)
    fun loadChatMessagesThrowsExceptionIfChannelNotExist() {
        every { channelServiceMock.existsChannel("not-a-channel") } returns false

        service.loadChatMessages("not-a-channel")
    }

    @Test
    @Throws(ChannelNotFoundException::class)
    fun saveChatMessage() {
        every { channelServiceMock.existsChannel("dev") } returns true
        every {
            chatMessageRepository.save(any<ChatMessage>())
        } answers {
            this.value
        }

        val savedMessage = service.saveChatMessage("dev", "sender@test.de", "Hello World!")

        verify { chatMessageRepository.save(any<ChatMessage>()) }

        assertEquals("dev", savedMessage.channelId)
        assertEquals("sender@test.de", savedMessage.sender)
        assertEquals("Hello World!", savedMessage.message)
        assertTrue(Duration.between(savedMessage.creationTimestamp, Instant.now()).abs().seconds < 3)
    }

    @Test(expected = ChannelNotFoundException::class)
    @Throws(ChannelNotFoundException::class)
    fun createChatMessagesThrowsExceptionIfChannelNotExist() {

        every { channelServiceMock.existsChannel("not-a-channel") } returns false

        service.saveChatMessage("not-a-channel", "sender@test.de", "Hello World!")
    }
}
