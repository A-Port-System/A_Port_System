package com.aport.flight.domain;

import java.io.Serializable;

public class FlightNotice implements Serializable {
    private String title;
    private String message;

    public FlightNotice(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
