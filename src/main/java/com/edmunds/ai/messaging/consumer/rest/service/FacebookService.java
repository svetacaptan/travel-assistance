package com.edmunds.ai.messaging.consumer.rest.service;

import com.edmunds.ai.messaging.consumer.rest.model.facebook.Entry;
import com.edmunds.ai.messaging.consumer.rest.model.facebook.Message;
import com.edmunds.ai.messaging.consumer.rest.model.facebook.Messaging;
import com.edmunds.ai.messaging.consumer.rest.model.facebook.Person;
import com.edmunds.ai.messaging.consumer.rest.model.facebook.Webhook;
import com.edmunds.ai.messaging.consumer.rest.utils.FacebookRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by Svitlana_Zmiivska on 7/28/2016.
 */
@Service
public class FacebookService {

    @Autowired
    private FacebookRestTemplate facebookRestTemplate;


    public void handleWebhook(Webhook webhook) {
        List<Entry> entries = webhook.getEntry();
        if (!CollectionUtils.isEmpty(entries)) {
            for (Entry entry : entries) {
                List<Messaging> messagings = entry.getMessaging();
                if (!CollectionUtils.isEmpty(messagings)) {
                    for (Messaging messaging : messagings) {
                        Message message = messaging.getMessage();
                        if (message != null) {
                            String text = message.getText();
                            if (!StringUtils.isEmpty(text)) {
                                sendMessage(messaging.getSender(), message);
                            }
                        }
                    }
                }
            }
        }
    }

    private void sendMessage(Person sender, Message message) {

        //SEND answer to user
        facebookRestTemplate.sendMessage(sender, message);
    }
}
