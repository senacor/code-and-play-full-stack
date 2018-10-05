package com.senacor.code.fullstack.chat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean

@SpringBootApplication
open class ChatApplication {

    @Bean
    open fun commonsRequestLoggingFilterRegistration(filter: RequestLoggingFilter): FilterRegistrationBean<*> =
        FilterRegistrationBean(filter)
}

// This is the main function
fun main(args: Array<String>) {
    runApplication<ChatApplication>(*args)
}
