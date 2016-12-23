package com.edmunds.ai.messaging.consumer.rest.utils;

import com.edmunds.ai.messaging.consumer.rest.model.facebook.Entry;
import com.edmunds.ai.messaging.consumer.rest.model.facebook.Message;
import com.edmunds.ai.messaging.consumer.rest.model.facebook.Person;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by Svitlana_Zmiivska on 7/29/2016.
 */
public class FacebookRestTemplateTest {

    @InjectMocks
    private FacebookRestTemplate facebookRestTemplate;

    @Mock
    private RestTemplate restTemplate;

    @BeforeClass
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSendMessage() throws Exception {
        Person sender = new Person("124");
        Message message = new Message("text");
        facebookRestTemplate.sendMessage(sender, message);
        verify(restTemplate, times(1)).postForObject(eq(FacebookRestTemplate.URL_FACEBOOK_MESSAGES), any(Entry.class), eq(String.class));
    }
}