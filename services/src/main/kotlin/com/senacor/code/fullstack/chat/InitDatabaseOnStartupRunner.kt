package com.senacor.code.fullstack.chat

import com.senacor.code.fullstack.chat.channel.Channel
import com.senacor.code.fullstack.chat.channel.ChannelRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("!production")
class InitDatabaseOnStartupRunner : CommandLineRunner {

    private val log = LoggerFactory.getLogger(InitDatabaseOnStartupRunner::class.java)

    @Autowired
    private lateinit var channelRepository: ChannelRepository

    @Throws(Exception::class)
    override fun run(vararg args: String) {
        // create some channels
        channelRepository.save(Channel("general", "general"))
        channelRepository.save(Channel("dev", "dev"))
        channelRepository.save(Channel("humor", "humor"))

        // fetch all channels
        log.info("Channels in Mongo DB:")
        for (msg in channelRepository.findAll()) {
            log.info("    $msg")
        }
    }
}