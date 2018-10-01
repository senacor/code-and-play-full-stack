package com.senacor.code.fullstack.chat.message

import org.hibernate.validator.constraints.Length
import org.springframework.data.annotation.Id
import java.time.Instant
import javax.validation.constraints.Email

data class ChatMessage(
    var channelId: String?,
    @field:Email
    val sender: String,
    @field:Length(min = 3, max = 140)
    val message: String,
    val creationTimestamp: Instant = Instant.now(),
    @Id val id: String? = null
)
