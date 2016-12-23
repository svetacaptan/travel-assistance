package com.edmunds.ai.messaging.consumer.rest.exception;

/**
 * Created by Svitlana_Zmiivska on 7/28/2016.
 */
public class FacebookForbiddenException extends RuntimeException {

    public FacebookForbiddenException(String message) {
        super(message);
    }

    public FacebookForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public FacebookForbiddenException(Throwable cause) {
        super(cause);
    }

    public FacebookForbiddenException() {
    }
}
