package com.senacor.code.fullstack.chat.message

import com.senacor.code.fullstack.chat.channel.ChannelService
import org.springframework.stereotype.Service

@Service
class ChatMessageService(
    private val channelService: ChannelService,
    private var chatMessageRepository: ChatMessageRepository
) {

    fun loadChatMessages(channelId: String): List<ChatMessage> {
        if (!channelService.existsChannel(channelId)) {
            throw ChannelNotFoundException()
        }
        return chatMessageRepository.findByChannelIdOrderByCreationTimestampAsc(channelId)
    }
}