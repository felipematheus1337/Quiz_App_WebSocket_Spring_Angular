package com.appquiz.chat.model.enums;

public enum MessageType {

        QUESTION("QUESTION"),
        CUSTOM("CUSTOM"),

        LAST_QUESTION("LAST");

        private final String key;

        private MessageType(String key) {
            this.key = key;
        }

        public String getKey() {
            return this.key;
        }
}
