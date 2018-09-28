package com.senacor.code.fullstack.chat.message

import org.junit.Assert.assertEquals
import org.junit.Test

class ChatMessageServiceTest {
    private val service = ChatMessageService()

    @Test
    fun fetchChatMessages() {
        val result = service.loadChatMessages("dev")

        assertEquals(2, result.size)
        assertEquals("Hello", result[0].message)
        assertEquals("World!", result[1].message)
    }
}