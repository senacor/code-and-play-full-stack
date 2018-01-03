package com.senacor.code.fullstack.chat;

import com.senacor.code.fullstack.chat.domain.ChatMessage;
import com.senacor.code.fullstack.chat.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ChatApplication implements CommandLineRunner {

	@Autowired
	private ChatMessageRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();

		// save a couple of chat messages
		repository.save(new ChatMessage("dev", "sender@test.de", "Hello"));
		repository.save(new ChatMessage("dev", "sender@test.de", "World!"));

		// fetch all customers
		System.out.println("ChatMessages found with findAll():");
		System.out.println("-------------------------------");
		for (ChatMessage msg : repository.findAll()) {
			System.out.println(msg);
		}
	}
}
