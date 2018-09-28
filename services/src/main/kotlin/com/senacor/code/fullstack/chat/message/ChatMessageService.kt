package com.senacor.code.fullstack.chat.message

val messages = listOf(
    ChatMessage("dev", "julia@test.de", "Hello"),
    ChatMessage("dev", "julia@test.de", "World!"),
    ChatMessage("general", "max@test.de", "Have fun!")
)

class ChatMessageService {

    fun loadChatMessages(channelId: String): List<ChatMessage> {
        return messages.filter { it.channelId ==  channelId }
    }
}