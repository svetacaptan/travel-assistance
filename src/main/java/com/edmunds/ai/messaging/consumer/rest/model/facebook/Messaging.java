package com.edmunds.ai.messaging.consumer.rest.model.facebook;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Svitlana_Zmiivska on 7/28/2016.
 */
public class Messaging {

    @JsonProperty("sender")
    private Person sender;

    @JsonProperty("recipient")
    private Person recipient;

    private Message message;

    public Person getSender() {
        return sender;
    }

    public Person getRecipient() {
        return recipient;
    }

    public Message getMessage() {
        return message;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public void setRecipient(Person recipient) {
        this.recipient = recipient;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Messaging{");
        sb.append("sender=").append(sender);
        sb.append(", recipient=").append(recipient);
        sb.append(", message=").append(message);
        sb.append('}');
        return sb.toString();
    }

}
