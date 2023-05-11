package com.fire.report.domain.exception;

public class EventMissingException extends RuntimeException {
    public EventMissingException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public EventMissingException(String exMessage) {
        super(exMessage);
    }
}
