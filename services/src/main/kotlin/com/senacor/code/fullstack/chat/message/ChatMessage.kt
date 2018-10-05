package com.senacor.code.fullstack.chat.message

import org.springframework.data.annotation.Id
import java.time.Instant

data class ChatMessage(@Id val channelId: String, val sender: String, val message: String, val creationTimestamp: Instant = Instant.now())