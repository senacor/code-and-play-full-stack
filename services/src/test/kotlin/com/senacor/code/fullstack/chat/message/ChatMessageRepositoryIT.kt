package com.senacor.code.fullstack.chat.message

import com.senacor.code.fullstack.chat.IntegrationTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import java.time.Instant
import org.springframework.test.context.junit4.SpringRunner

@IntegrationTest
@RunWith(SpringRunner::class)
class ChatMessageRepositoryIT {

    @Autowired
    private lateinit var repository: ChatMessageRepository

    @Before
    fun setup() {
        // ensure we are staring without any chat messages
        repository.deleteAll()
    }

    @After
    fun cleanup() {
        repository.deleteAll()
    }

    @Test
    fun saveAndLoadChatMessages() {
        assertTrue(repository.findAll().isEmpty())

        val timeOne = Instant.now().minusSeconds(35)
        val messageOne = repository.save(ChatMessage("dev", "sender@test.de", "Hello", timeOne))

        assertEquals(1, repository.findAll().size.toLong())

        val timeTwo = Instant.now()
        val messageTwo = repository.save(ChatMessage("dev", "sender@test.de", "World!", timeTwo))

        val result = repository.findAll()
        assertEquals(listOf(messageOne, messageTwo), result)
    }

    @Test
    fun testFindByChannelIdOrderByCreationTimestampAsc() {
        assertTrue(repository.findAll().isEmpty())
        repository.save(ChatMessage("general", "sender@test.de", "let's"))
        repository.save(ChatMessage("dev", "sender@test.de", "Hello"))
        repository.save(ChatMessage("general", "sender@test.de", "test"))
        repository.save(ChatMessage("dev", "sender@test.de", "World!"))
        repository.save(ChatMessage("general", "sender@test.de", "mongo"))

        val messages = repository.findByChannelIdOrderByCreationTimestampDesc("general")

        assertEquals(3, messages.size.toLong())
    }


}