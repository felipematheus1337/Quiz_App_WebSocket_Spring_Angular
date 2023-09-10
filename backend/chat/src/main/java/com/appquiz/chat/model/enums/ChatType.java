package com.appquiz.chat.model.enums;

public enum ChatType {
    DBZ("DBZ"),
    GREETINGS("GREETINGS"),
    MMA("MMA"),
    CINEMA("CINEMA");

    private final String key;

    private ChatType(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}