package com.senacor.code.fullstack.chat.repository;

import com.senacor.code.fullstack.chat.domain.Channel;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data repository for the communication channels.
 */
public interface ChannelRepository extends MongoRepository<Channel, String> {

}
