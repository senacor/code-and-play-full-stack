package com.senacor.code.fullstack.chat.message

import java.time.Instant

data class ChatMessage(
    val channelId: String,
    val sender: String,
    val message: String,
    val creationTimestamp: Instant = Instant.now()
)