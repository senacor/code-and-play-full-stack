package com.senacor.code.fullstack.chat

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.context.ActiveProfiles
import java.lang.annotation.Inherited

@Inherited
@SpringBootTest(
    webEnvironment = RANDOM_PORT,
    classes = [ChatApplication::class]
)
@ActiveProfiles("test")
annotation class IntegrationTest
