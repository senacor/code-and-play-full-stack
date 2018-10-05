package com.senacor.code.fullstack.chat.message

import com.senacor.code.fullstack.chat.channel.ChannelNotFoundException
import com.senacor.code.fullstack.chat.channel.ChannelService
import org.springframework.stereotype.Service

val mockMessages = listOf(
        ChatMessage("dev", "julia@test.de", "Hello"),
        ChatMessage("dev", "julia@test.de", "World!"),
        ChatMessage("general", "max@test.de", "Have fun!"),
        ChatMessage("general", "tcuje@freenet.de", "Hello my name is Thomas")
)

@Service
class ChatMessageService(private val channelService: ChannelService) {

    fun loadMessages(channelId: String): List<ChatMessage> {
        if (channelService.existsChannel(channelId)) {
            return mockMessages.filter{ it.channelId == channelId }
        } else {
            throw ChannelNotFoundException()
        }
    }

}