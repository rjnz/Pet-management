package ru.rjnz.petmanagement.util.exception;

import org.springframework.http.HttpStatus;

public class ErrorInfo {
    private final String url;
    private final String status;
    private final String[] details;

    public ErrorInfo(CharSequence url, HttpStatus status, String... details) {
        this.url = url.toString();
        this.status = status.toString();
        this.details = details;
    }
}