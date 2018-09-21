package com.senacor.code.fullstack.chat

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner

@IntegrationTest
@RunWith(SpringRunner::class)
class ChatApplicationIntegrationTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun healthEndpointIsAvailable() {

        val result = restTemplate.getForEntity<Map<String, String>>("/actuator/health")

        assertEquals(HttpStatus.OK, result.statusCode)
        assertEquals(MediaType.APPLICATION_JSON_UTF8, result.headers.contentType)
        assertEquals("UP", result.body!!["status"])
    }
}