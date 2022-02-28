package com.example.bookstore.exception;

@SuppressWarnings("serial")
public class RestExceptionBase extends RuntimeException {
    private String messageId;
    private String debugId;

    public RestExceptionBase(String message, String messageId, String debugId) {
        super(message);
        this.messageId = messageId;
        this.debugId = debugId;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getDebugId() {
        return debugId;
    }
}
