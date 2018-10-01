package com.senacor.code.fullstack.chat.message

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

const val CHAT_MESSAGES_PATH = "/api/channels/{channelId}/messages"

@RestController
@RequestMapping(CHAT_MESSAGES_PATH)
class ChatMessageController(private var messageService: ChatMessageService) {

    @GetMapping
    fun loadChatMessages(@PathVariable("channelId") channelId: String) = messageService.loadChatMessages(channelId)

    @PostMapping
    fun newChatMessages(@PathVariable("channelId") channel: String, @RequestBody chatMessage: ChatMessage): ResponseEntity<Void> {
        val newChatMessage = messageService.saveChatMessage(channel, chatMessage.sender, chatMessage.message)

        val location = UriComponentsBuilder.newInstance().path(CHAT_MESSAGES_PATH)
            .pathSegment(newChatMessage.id).buildAndExpand(channel)
        return ResponseEntity.created(location.toUri()).build()
    }
}
