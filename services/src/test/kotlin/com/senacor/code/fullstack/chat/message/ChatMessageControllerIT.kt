package com.senacor.code.fullstack.chat.message

import com.senacor.code.fullstack.chat.IntegrationTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@IntegrationTest
@RunWith(SpringRunner::class)
class ChatMessageControllerIT {

    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var wac: WebApplicationContext

    @Before
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }

    @Test
    fun loadMessages() {
        val channelId = "dev"

        mockMvc.perform(get("/api/channels/{channelId}/messages", channelId).accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$[0].message").value("Hello"))
            .andExpect(jsonPath("$[1].message").value("World!"))
    }

    @Test
    fun loadMessagesForNotExistingChannel() {
        val channel = "not-a-channel"
        mockMvc.perform(get("/api/v1/{channel}/messages", channel)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isNotFound)
    }
}