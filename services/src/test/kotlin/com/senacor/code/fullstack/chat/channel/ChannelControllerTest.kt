package com.senacor.code.fullstack.chat.channel

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test

class ChannelControllerTest {

    private val serviceMock = mockk<ChannelService>()

    private val controller = ChannelController(serviceMock)

    @Test
    fun loadChannels() {
        val expected = listOf(
                Channel("foo", "foo"),
                Channel("foo", "bar"))
        every { serviceMock.loadChannels() } returns expected

        val result = controller.loadChannels()

        assertEquals(expected, result)
        verify { serviceMock.loadChannels() }
    }

}
