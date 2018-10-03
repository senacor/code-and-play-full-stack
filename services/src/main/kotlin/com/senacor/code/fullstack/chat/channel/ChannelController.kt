package com.senacor.code.fullstack.chat.channel

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/channels")
class ChannelController(private val channelService: ChannelService) {

    @GetMapping
    fun loadChannels() = channelService.loadChannels()

}