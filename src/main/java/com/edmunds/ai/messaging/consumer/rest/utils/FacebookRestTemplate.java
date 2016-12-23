package com.edmunds.ai.messaging.consumer.rest.utils;

import com.edmunds.ai.messaging.consumer.rest.exception.FacebookForbiddenException;
import com.edmunds.ai.messaging.consumer.rest.model.facebook.Message;
import com.edmunds.ai.messaging.consumer.rest.model.facebook.Person;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Svitlana_Zmiivska on 7/28/2016.
 */
@Component
public class FacebookRestTemplate {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${facebook.page.access.token}")
    private String pageAccessToken;

    protected static final String URL_FACEBOOK_MESSAGES = "https://graph.facebook.com/v2.6/me/messages";

    public void sendMessage(Person sender, Message message) {
        try {
            JsonObject jsonObject = createJsonObject(sender, message);
            HttpHeaders headers = createHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);
            restTemplate.postForObject(URL_FACEBOOK_MESSAGES, entity, String.class);
        } catch (HttpClientErrorException e) {
            throw new FacebookForbiddenException(e);
        }
    }

    private JsonObject createJsonObject(Person sender, Message message) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("recipient", sender.getId());
        jsonObject.addProperty("message", message.getText());
        return jsonObject;
    }

    private HttpHeaders createHeaders() {
        String authString = "access_token" + ":" + pageAccessToken;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + authString);
        return headers;
    }
}
