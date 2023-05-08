package com.fire.truck.infrastructure.adapter.secondary.exception;

public class TruckNotFoundException extends RuntimeException {
    public TruckNotFoundException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public TruckNotFoundException(String exMessage) {
        super(exMessage);
    }
}
