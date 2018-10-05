package com.senacor.code.fullstack.chat.message

import com.senacor.code.fullstack.chat.IntegrationTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringRunner

@IntegrationTest
@RunWith(SpringRunner::class)
class ChatMessageRepositoryIT {

    @Autowired
    private lateinit var chatMessageRepository: ChatMessageRepository

    @Before
    fun setup() {
        chatMessageRepository.deleteAll()
    }

    @Test
    fun loadChannels() {
        val expectedMessages = listOf(
                ChatMessage("test", "tcuje@freenet.de", "Hello World"),
                ChatMessage("test", "tcuje@freenet.de", "This is not a test!")
        )
        chatMessageRepository.saveAll(expectedMessages)

        val fetchedMessages = chatMessageRepository.findAll()

        assertEquals(expectedMessages, fetchedMessages)
    }

    @Test
    fun testFindByChannelIdOrderByCreationTimestamp() {
        val expectedMessages = listOf(
                ChatMessage("test", "tcuje@freenet.de", "Hello World"),
                ChatMessage("test", "tcuje@freenet.de", "This is not a test!")
        )
        chatMessageRepository.saveAll(expectedMessages)

        val fetchedMessages = chatMessageRepository.findByChannelIdOrderByCreationTimestamp("test")

        assertEquals(expectedMessages, fetchedMessages)
    }

}