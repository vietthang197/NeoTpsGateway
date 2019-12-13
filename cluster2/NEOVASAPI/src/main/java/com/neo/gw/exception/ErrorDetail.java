package com.neo.gw.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ErrorDetail implements Serializable {

    private long timestamp;

    private String message;

    private int status;

    private String error;

    public ErrorDetail() {
    }

    public ErrorDetail(long timestamp, String message, int status, String error) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
        this.error = error;
    }

    public double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
