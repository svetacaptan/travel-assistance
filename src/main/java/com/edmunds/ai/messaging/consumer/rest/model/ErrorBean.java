package com.edmunds.ai.messaging.consumer.rest.model;

/**
 * Error message bean.
 * Created by Svitlana_Zmiivska on 5/28/2016.
 */
public class ErrorBean {

    /**
     * Error code.
     */
    private int code;

    /**
     * Error message.
     */
    private String message;

    public ErrorBean(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorBean{");
        sb.append("code=").append(code);
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }

}