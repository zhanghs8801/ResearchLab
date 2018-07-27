package com.patsnap.learn.com.patsnap.learn.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder({"response", "cost"})
public class EntityResponse<T> implements Serializable {

    @JsonProperty("response")
    private T body;

    private long cost;

    public EntityResponse(T responseBody, long cost) {
        this.body = responseBody;
        this.cost = cost;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }
}
