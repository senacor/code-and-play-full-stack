package com.senacor.code.fullstack.chat.message

import com.senacor.code.fullstack.chat.channel.ChannelService
import org.junit.Assert.assertEquals
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

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

        every { chatMessageRepository.findByChannelIdOrderByCreationTimestampAsc("dev") } returns expectedList

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
}
