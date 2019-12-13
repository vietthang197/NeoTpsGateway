package com.neo.gw.controller.vm;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Map;

public class DefaultRequestVM implements Serializable {

    @JsonProperty("authen")
    private Map<String, String> authen;

    @JsonProperty("request")
    private Map<String, String> request;

    public DefaultRequestVM() {
    }

    public DefaultRequestVM(Map<String, String> authen, Map<String, String> request) {
        this.authen = authen;
        this.request = request;
    }

    public Map<String, String> getAuthen() {
        return authen;
    }

    public void setAuthen(Map<String, String> authen) {
        this.authen = authen;
    }

    public Map<String, String> getRequest() {
        return request;
    }

    public void setRequest(Map<String, String> request) {
        this.request = request;
    }
}
