package com.fire.truck.domain.exception;

public class EmptyTruckRequestException extends RuntimeException {
    public EmptyTruckRequestException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public EmptyTruckRequestException(String exMessage) {
        super(exMessage);
    }
}
