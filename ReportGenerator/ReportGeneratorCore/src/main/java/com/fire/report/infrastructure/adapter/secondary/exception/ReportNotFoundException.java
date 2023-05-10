package com.fire.report.infrastructure.adapter.secondary.exception;

public class ReportNotFoundException extends RuntimeException {
    public ReportNotFoundException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public ReportNotFoundException(String exMessage) {
        super(exMessage);
    }
}
