package com.senacor.code.fullstack.chat;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

@Component
public class RequestLoggingFilter extends CommonsRequestLoggingFilter {

    public RequestLoggingFilter() {
        setIncludeQueryString(true);
        setIncludePayload(true);
        setMaxPayloadLength(10000);
        setIncludeHeaders(false);
        setBeforeMessagePrefix(" ");
        setBeforeMessageSuffix("");
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        super.beforeRequest(request, request.getMethod() +  message);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        // don't log after request
    }
}
