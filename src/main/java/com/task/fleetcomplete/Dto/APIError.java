package com.task.fleetcomplete.Dto;

public class APIError {

    private int statusCode;

    public APIError(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
