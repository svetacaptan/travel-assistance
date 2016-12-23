package com.edmunds.ai.messaging.consumer.rest.controller;

/**
 * Created by Svitlana_Zmiivska on 7/28/2016.
 */

import com.edmunds.ai.messaging.consumer.rest.exception.FacebookForbiddenException;
import com.edmunds.ai.messaging.consumer.rest.model.ErrorBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Svitlana_Zmiivska on 5/28/2016.
 * Handler all exception in application.
 */
@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(FacebookForbiddenException.class)
    public ErrorBean handleException(FacebookForbiddenException ex) {
        return new ErrorBean(HttpStatus.FORBIDDEN.value(), ex.getMessage());
    }

}

