package com.senacor.code.fullstack.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean commonsRequestLoggingFilterRegistration(RequestLoggingFilter filter) {
		return new FilterRegistrationBean(filter);
	}
}
