package com.fptugroup6.phoneshop.model;

public class msgModelClass {
    String message;
    String username;
    long timerStamp;

    public msgModelClass() {
    }

    public msgModelClass(String message, String username, long timerStamp) {
        this.message = message;
        this.username = username;
        this.timerStamp = timerStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getTimerStamp() {
        return timerStamp;
    }

    public void setTimerStamp(long timerStamp) {
        this.timerStamp = timerStamp;
    }
}
