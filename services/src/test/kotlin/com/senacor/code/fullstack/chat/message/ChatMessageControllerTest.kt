package com.senacor.code.fullstack.chat.message

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test

class ChatMessageControllerTest {

    private var serviceMock = mockk<ChatMessageService>()

    private var controller = ChatMessageController(serviceMock)

    @Test
    fun loadChannelMessages() {
        val expected = listOf(
            ChatMessage("dev", "s1", "m1"),
            ChatMessage("dev", "s2", "m2")
        )
        every { serviceMock.loadChatMessages("dev") } returns expected

        val result = controller.loadChatMessages("dev")

        assertEquals(expected, result)
        verify { serviceMock.loadChatMessages("dev") }
    }
}