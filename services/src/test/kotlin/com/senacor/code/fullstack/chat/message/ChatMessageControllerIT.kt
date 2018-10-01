package com.senacor.code.fullstack.chat.message

import com.fasterxml.jackson.databind.ObjectMapper
import com.senacor.code.fullstack.chat.IntegrationTest
import org.hamcrest.CoreMatchers.startsWith
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@IntegrationTest
@RunWith(SpringRunner::class)
class ChatMessageControllerIT {

    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var wac: WebApplicationContext

    @Autowired
    private lateinit var repository: ChatMessageRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Before
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }

    @Test
    fun loadMessages_channelGeneral() {
        val channelId = "general"
        repository.save(ChatMessage(channelId, "max@test.de", "Hello"))
        repository.save(ChatMessage(channelId, "max@test.de", "World!"))


        mockMvc.perform(get("/api/channels/{channelId}/messages", channelId).accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            // Reihenfolge umgekehrt, weil wir ja von Asc auf Desc gewechselt haben
            .andExpect(jsonPath("$[1].message").value("Hello"))
            .andExpect(jsonPath("$[0].message").value("World!"))
    }

    @Test
    fun loadMessages_channelDev() {
        val channelId = "dev"
        repository.save(ChatMessage(channelId, "julia@test.de", "Have fun!"))

        mockMvc.perform(get("/api/channels/{channelId}/messages", channelId).accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$[0].message").value("Have fun!"))
    }

    @Test
    fun loadMessagesForNotExistingChannel() {
        val channel = "not-a-channel"
        mockMvc.perform(get("/api/{channel}/messages", channel)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isNotFound)
    }

    @Test
    @Throws(Exception::class)
    fun newMessages() {
        val channel = "dev"
        val newMessage = ChatMessage(channel, "xy@test.de", "My first message")

        mockMvc.perform(post("/api/channels/{channel}/messages", channel)
                .content(objectMapper.writeValueAsString(newMessage))
                .contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isCreated)
                .andExpect(header().string("location", startsWith("/api/channels/dev/messages/")))
                .andDo { result ->
                    // Check if the new message can be found in the database
                    val location = result.response.getHeader("location")
                    val newMessageId = location!!.substring(location.lastIndexOf("/") + 1)
                    val savedMessage = repository.findById(newMessageId).get()
                    assertEquals("xy@test.de", savedMessage.sender)
                    assertEquals("My first message", savedMessage.message)
                }
    }
}