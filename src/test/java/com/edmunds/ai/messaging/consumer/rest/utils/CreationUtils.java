package com.edmunds.ai.messaging.consumer.rest.utils;

import com.edmunds.ai.messaging.consumer.rest.model.facebook.Entry;
import com.edmunds.ai.messaging.consumer.rest.model.facebook.Message;
import com.edmunds.ai.messaging.consumer.rest.model.facebook.Messaging;
import com.edmunds.ai.messaging.consumer.rest.model.facebook.Person;
import com.edmunds.ai.messaging.consumer.rest.model.facebook.Webhook;

import java.util.Arrays;

/**
 * Created by Svitlana_Zmiivska on 7/29/2016.
 */
public class CreationUtils {

    public static Webhook createWebHook(String senderId, String recipientId) {
        Message message = new Message();
        message.setText("text");

        Messaging messaging = new Messaging();
        messaging.setMessage(message);
        messaging.setSender(new Person(senderId));
        messaging.setRecipient(new Person(recipientId));

        Entry entry = new Entry();
        entry.setId("1");
        entry.setMessaging(Arrays.asList(messaging));

        Webhook webhook = new Webhook();
        webhook.setObject("page");
        webhook.setEntry(Arrays.asList(entry));
        return webhook;
    }

}
