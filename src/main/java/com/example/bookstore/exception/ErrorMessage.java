package com.example.bookstore.exception;

public class ErrorMessage {
    private String messageId;
    private String debugId;
    private String message;

    public ErrorMessage() {
    }

    public ErrorMessage(String messageId, String debugId, String message) {
        this.messageId = messageId;
        this.debugId = debugId;
        this.message = message;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getDebugId() {
        return debugId;
    }

    public void setDebugId(String debugId) {
        this.debugId = debugId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
