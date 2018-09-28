package com.senacor.code.fullstack.chat.message

import com.senacor.code.fullstack.chat.channel.ChannelService
import org.springframework.stereotype.Service

val messages = listOf(
    ChatMessage("dev", "julia@test.de", "Hello"),
    ChatMessage("dev", "julia@test.de", "World!"),
    ChatMessage("general", "max@test.de", "Have fun!")
)

@Service
class ChatMessageService(private val channelService: ChannelService) {

    fun loadChatMessages(channelId: String): List<ChatMessage> {
        if (!channelService.existsChannel(channelId)) {
            throw ChannelNotFoundException()
        }
        return messages.filter { it.channelId == channelId }
    }
}