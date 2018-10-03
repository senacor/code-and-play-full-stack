package com.senacor.code.fullstack.chat.channel

import org.springframework.data.annotation.Id

data class Channel(@Id val channelId: String, val name: String)