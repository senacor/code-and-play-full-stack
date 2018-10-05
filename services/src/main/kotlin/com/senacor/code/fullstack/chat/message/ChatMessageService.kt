package com.senacor.code.fullstack.chat.message

import org.springframework.stereotype.Service

@Service
class ChatMessageService {
    val mockMessages = listOf(
            ChatMessage("dev", "julia@test.de", "Hello"),
            ChatMessage("dev", "julia@test.de", "World!"),
            ChatMessage("general", "max@test.de", "Have fun!")
    )

    fun fetchMessages(channelId: String): List<ChatMessage> {
        return mockMessages
    }
}