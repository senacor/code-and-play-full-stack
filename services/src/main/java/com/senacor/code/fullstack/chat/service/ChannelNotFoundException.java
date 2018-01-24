package com.senacor.code.fullstack.chat.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to represent the case when a requested communication channel does not exist.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Channel")
public class ChannelNotFoundException extends RuntimeException {
}
