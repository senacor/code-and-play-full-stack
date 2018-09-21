package com.senacor.code.fullstack.chat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ChatApplication

fun main(args: Array<String>) {
    runApplication<ChatApplication>(*args)
}
