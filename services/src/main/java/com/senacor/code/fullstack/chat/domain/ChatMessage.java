package com.senacor.code.fullstack.chat.domain;

import java.time.Instant;
import java.util.Objects;

/**
 * Domain model for chat message.
 */
public class ChatMessage {

    private String id;

    private String channelId;

    private String sender;

    private String message;

    private Instant creationTimestamp;

    public ChatMessage() {
        this.creationTimestamp = Instant.now();
    }

    public ChatMessage(String channelId, String sender, String message) {
        this();
        this.channelId = channelId;
        this.sender = sender;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Instant creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessage that = (ChatMessage) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(channelId, that.channelId) &&
                Objects.equals(sender, that.sender) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, channelId, sender, message);
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id=" + id +
                ", channelId='" + channelId + '\'' +
                ", sender='" + sender + '\'' +
                ", message='" + message + '\'' +
                ", creationTimestamp=" + creationTimestamp +
                '}';
    }
}
