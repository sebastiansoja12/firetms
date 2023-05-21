package com.fire.truck.infrastructure.adapter.secondary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TruckNotFoundException extends RuntimeException {
    public TruckNotFoundException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public TruckNotFoundException(String exMessage) {
        super(exMessage);
    }
}
