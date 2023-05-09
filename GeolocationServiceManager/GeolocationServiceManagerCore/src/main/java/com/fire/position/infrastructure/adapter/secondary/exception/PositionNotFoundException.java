package com.fire.position.infrastructure.adapter.secondary.exception;

public class PositionNotFoundException extends RuntimeException {
    public PositionNotFoundException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public PositionNotFoundException(String exMessage) {
        super(exMessage);
    }
}
