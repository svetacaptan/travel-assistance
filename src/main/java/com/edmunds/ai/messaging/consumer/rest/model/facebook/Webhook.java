package com.edmunds.ai.messaging.consumer.rest.model.facebook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Svitlana_Zmiivska on 7/28/2016.
 */
public class Webhook {

    private List<Entry> entry;

    private String object;

    public String getObject() {
        return object;
    }

    public List<Entry> getEntry() {
        return new ArrayList<Entry>(entry);
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }

    public void setObject(String object) {
        this.object = object;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Webhook{");
        sb.append("entry=").append(entry);
        sb.append(", object='").append(object).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
