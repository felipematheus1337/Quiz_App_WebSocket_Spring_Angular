package com.appquiz.chat.model.user;

public enum StatusUser {
    ANSWERING("ANSWERING"),
    FINISHED("FINISHED");


    private String key;

    private StatusUser(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}