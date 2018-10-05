package com.senacor.code.fullstack.chat.channel

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ChannelNotFoundException: Exception()
