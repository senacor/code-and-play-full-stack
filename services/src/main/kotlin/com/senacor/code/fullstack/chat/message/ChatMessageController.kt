package com.senacor.code.fullstack.chat.message

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/channels/{channelId}/messages")
class ChatMessageController(private var messageService: ChatMessageService) {

    @GetMapping
    fun loadChatMessages(@PathVariable("channelId") channelId: String) = messageService.loadChatMessages(channelId)
}