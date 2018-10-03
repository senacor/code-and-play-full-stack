package com.senacor.code.fullstack.chat.channel

import org.springframework.stereotype.Service

@Service
class ChannelService(private var channelRepository: ChannelRepository) {

    fun loadChannels(): List<Channel> = channelRepository.findAll()

    fun existsChannel(channelId: String) = channelRepository.existsById(channelId)
}