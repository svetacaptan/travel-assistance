package com.edmunds.ai.messaging.consumer.rest.controller;

import com.edmunds.ai.messaging.consumer.rest.exception.FacebookForbiddenException;
import com.edmunds.ai.messaging.consumer.rest.model.facebook.Webhook;
import com.edmunds.ai.messaging.consumer.rest.service.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * Created by Svitlana_Zmiivska on 2016.
 */
@RestController
@RequestMapping(value = "/messages/webhook")
public class FacebookController {

    @Value("${facebook.mode}")
    private String facebookMode;

    @Value("${facebook.verify.token}")
    private String facebookVerifyToken;

    @Autowired
    private FacebookService facebookService;

    @RequestMapping(method = RequestMethod.GET)
    public String getWebhook(@RequestParam("hub.mode") String mode, @RequestParam("hub.verify_token") String verifyToken,
                             @RequestParam("hub.challenge") String challenge) throws UnsupportedEncodingException {
        if (!facebookMode.equals(mode) ||
                !facebookVerifyToken.equals(verifyToken)) {
            throw new FacebookForbiddenException("Failed validation. Make sure the validation tokens match.");
        }
        return challenge;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void getWebhook(@RequestBody Webhook webhook) throws UnsupportedEncodingException {
        facebookService.handleWebhook(webhook);
    }

}

