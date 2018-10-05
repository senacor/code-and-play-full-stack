package com.senacor.code.fullstack.chat.message

import com.senacor.code.fullstack.chat.IntegrationTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

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
        mockMvc.perform(get("/api/channels/dev/messages").accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$[0].channelId").value("dev"))
            .andExpect(jsonPath("$[1].channelId").value("dev"))
    }

    @Test
    fun loadMessagesChannelNotExists() {
        mockMvc.perform(get("/api/channels/not-a-channel/messages").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound)
    }

}