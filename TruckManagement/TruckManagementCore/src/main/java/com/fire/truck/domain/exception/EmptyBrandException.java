package com.fire.truck.domain.exception;

public class EmptyBrandException extends RuntimeException {
    public EmptyBrandException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public EmptyBrandException(String exMessage) {
        super(exMessage);
    }
}
