package com.appquiz.chat.model.enums;

public enum MessageType {

        QUESTION("QUESTION"),
        CUSTOM("CUSTOM");

        private String key;

        private MessageType(String key) {
            this.key = key;
        }

        public String getKey() {
            return this.key;
        }
}
