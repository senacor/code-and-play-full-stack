package com.senacor.code.fullstack.chat.channel

import org.springframework.data.mongodb.repository.MongoRepository

interface ChannelRepository : MongoRepository<Channel, String>