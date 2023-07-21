package com.areaonline.user.modal;

public class Model_Chat {

    private String message;
    private String name;
    private String timestamp;

    public Model_Chat() {
    }

    public Model_Chat(String message, String name, String timestamp) {
        this.message = message;
        this.name = name;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}