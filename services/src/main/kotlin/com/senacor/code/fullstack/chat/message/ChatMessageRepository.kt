package com.senacor.code.fullstack.chat.message

import org.springframework.data.mongodb.repository.MongoRepository

interface ChatMessageRepository : MongoRepository<ChatMessage, String> {

    fun findByChannelIdOrderByCreationTimestamp(channelId: String): List<ChatMessage>

}
