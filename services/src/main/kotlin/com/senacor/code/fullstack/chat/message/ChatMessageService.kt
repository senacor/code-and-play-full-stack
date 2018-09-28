package com.senacor.code.fullstack.chat.message

import org.springframework.stereotype.Service

val messages = listOf(
    ChatMessage("dev", "julia@test.de", "Hello"),
    ChatMessage("dev", "julia@test.de", "World!"),
    ChatMessage("general", "max@test.de", "Have fun!")
)

@Service
class ChatMessageService {

    fun loadChatMessages(channelId: String): List<ChatMessage> {
        return messages.filter { it.channelId ==  channelId }
    }
}