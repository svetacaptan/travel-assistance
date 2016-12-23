package com.edmunds.ai.messaging.consumer.rest.model.facebook;

import java.util.List;

/**
 * Created by Svitlana_Zmiivska on 7/28/2016.
 */
public class Entry {

    private String id;

    private List<Messaging> messaging;

    public String getId() {
        return id;
    }

    public List<Messaging> getMessaging() {
        return messaging;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMessaging(List<Messaging> messaging) {
        this.messaging = messaging;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Entry{");
        sb.append("id='").append(id).append('\'');
        sb.append(", messaging=").append(messaging);
        sb.append('}');
        return sb.toString();
    }
}
