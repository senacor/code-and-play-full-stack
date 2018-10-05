package com.senacor.code.fullstack.chat.message

import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpHeaders



@RestController
@RequestMapping("/api/channels/{channelId}/messages")
class ChatMessageController(private val messageService: ChatMessageService) {

    @GetMapping
    fun loadMessages(@PathVariable("channelId") channelId: String) = messageService.loadMessages(channelId)

    @PostMapping
    fun saveMessage(@PathVariable("channelId") channelId: String, sender: String, message: String): ResponseEntity<Void> {
        val msg = messageService.createMessage(channelId, sender, message)
        val headers = HttpHeaders()
        headers.location = UriComponentsBuilder.newInstance()
                .path("/api/channels/{channelId}/messages/{messageId}")
                .buildAndExpand(msg.channelId, msg.messageId).toUri()
        return ResponseEntity<Void>(headers, HttpStatus.CREATED)
    }

}