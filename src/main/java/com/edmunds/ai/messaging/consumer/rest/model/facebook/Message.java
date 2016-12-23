package com.edmunds.ai.messaging.consumer.rest.model.facebook;

/**
 * Created by Svitlana_Zmiivska on 7/28/2016.
 */
public class Message {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Message() {
    }

    public Message(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Message{");
        sb.append("text='").append(text).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
