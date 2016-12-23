package com.edmunds.ai.messaging.consumer.rest.controller;

import com.edmunds.ai.messaging.consumer.rest.model.facebook.Webhook;
import com.edmunds.ai.messaging.consumer.rest.service.FacebookService;
import com.edmunds.ai.messaging.consumer.rest.utils.CreationUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Svitlana_Zmiivska on 7/29/2016.
 */
@ContextConfiguration({"classpath:/spring-config.xml"})
public class FacebookControllerTest extends AbstractTestNGSpringContextTests {

    private MockMvc mockMvc;

    @InjectMocks
    private FacebookController facebookController;

    @Mock
    private FacebookService facebookService;

    @InjectMocks
    private ExceptionHandlerController exceptionHandlerController;

    @BeforeClass
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(facebookController)
                .setControllerAdvice(exceptionHandlerController)
                .build();
    }

    @Test
    public void testGetWebhookVerifyTokenAndHubMode() throws Exception {
        String mode = "subscribe";
        String verifyToken = "sdgsrtergdx";
        String challenge = "challenge";

        setFields(mode, verifyToken);

        mockMvc.perform(get("/messages/webhook")
                .param("hub.mode", mode)
                .param("hub.verify_token", verifyToken)
                .param("hub.challenge", challenge))
                .andExpect(status().isOk())
                .andExpect(content().string(challenge));
    }

    @Test
    public void testGetWebhookVerifyTokenAndWrongHubMode() throws Exception {
        String expectedMode = "subscribe";
        String expectedVerifyToken = "sdgsrtergdx";

        String mode = "subscribeWrong";
        String verifyToken = "sdgsrtergdx";
        String challenge = "challenge";
        testGetWebhookWithWrongParameters(mode, verifyToken, challenge, expectedMode, expectedVerifyToken);
    }

    @Test
    public void testGetWebhookWrongVerifyTokenAndHubMode() throws Exception {
        String expectedMode = "subscribe";
        String expectedVerifyToken = "sdgsrtergdx";

        String mode = "subscribe";
        String verifyToken = "sdgsrtergdxWrong";
        String challenge = "challenge";
        testGetWebhookWithWrongParameters(mode, verifyToken, challenge, expectedMode, expectedVerifyToken);
    }

    private void testGetWebhookWithWrongParameters(String mode, String verifyToken, String challenge, String expectedMode, String expectedVerifyToken) throws Exception {

        setFields(expectedMode, expectedVerifyToken);

        mockMvc.perform(get("/messages/webhook")
                .param("hub.mode", mode)
                .param("hub.verify_token", verifyToken)
                .param("hub.challenge", challenge))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.code", is(HttpStatus.FORBIDDEN.value())))
                .andExpect(jsonPath("$.message", is("Failed validation. Make sure the validation tokens match.")));
    }

    private void setFields(String expectedMode, String expectedVerifyToken) {
        ReflectionTestUtils.setField(facebookController, "facebookMode", expectedMode);
        ReflectionTestUtils.setField(facebookController, "facebookVerifyToken", expectedVerifyToken);
    }

    @Test
    public void testGetWebhook1() throws Exception {
        String senderId = "4354365";
        String recipientId = "32436543";
        Webhook webhook = CreationUtils.createWebHook(senderId, recipientId);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer();
        String jsonWebhook = objectWriter.writeValueAsString(webhook);

        mockMvc.perform(post("/messages/webhook")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonWebhook))
                .andExpect(status().isOk());

        verify(facebookService, times(1)).handleWebhook(any(Webhook.class));
        verifyNoMoreInteractions(facebookService);
    }
}