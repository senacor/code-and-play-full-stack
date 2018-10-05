package com.senacor.code.fullstack.chat.message

import com.senacor.code.fullstack.chat.channel.ChannelNotFoundException
import com.senacor.code.fullstack.chat.channel.ChannelService
import org.springframework.stereotype.Service

@Service
class ChatMessageService(private val channelService: ChannelService, private val chatMessageRepository: ChatMessageRepository) {

    fun loadMessages(channelId: String): List<ChatMessage> {
        if (channelService.existsChannel(channelId)) {
            return chatMessageRepository.findAll().filter{ it.channelId == channelId }
        } else {
            throw ChannelNotFoundException()
        }
    }

    fun createMessage(channelId: String, sender: String, message: String): ChatMessage {
        if (channelService.existsChannel(channelId)) {
            val msg = ChatMessage(channelId, sender, message)
            chatMessageRepository.save(msg)
            return msg
        } else {
            throw ChannelNotFoundException()
        }
    }

}