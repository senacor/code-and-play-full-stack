package com.senacor.code.fullstack.chat

import org.springframework.stereotype.Component
import org.springframework.web.filter.CommonsRequestLoggingFilter
import javax.servlet.http.HttpServletRequest

@Component
class RequestLoggingFilter : CommonsRequestLoggingFilter() {
    init {
        isIncludeQueryString = true
        isIncludePayload = true
        maxPayloadLength = 10000
        isIncludeHeaders = false
        setBeforeMessagePrefix(" ")
        setBeforeMessageSuffix("")
    }

    override fun beforeRequest(request: HttpServletRequest?, message: String) {
        super.beforeRequest(request, request!!.method + message)
    }

    override fun afterRequest(request: HttpServletRequest?, message: String) {
        // don't log after request
    }
}