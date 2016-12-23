package com.edmunds.ai.messaging.consumer.rest.model.facebook;

/**
 * Created by Svitlana_Zmiivska on 7/28/2016.
 */
public class Person {

    private String id;

    public String getId() {
        return id;
    }

    public Person(String id) {
        this.id = id;
    }

    public Person() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
