package com.energize.test.company.advisor;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ExceptionResponse {
    private final String message;
    private final HttpStatus httpStatus;
    private final int statusCode;
    private final ZonedDateTime timestamp;

    public ExceptionResponse(String message,
                             HttpStatus httpStatus,
                             int statusCode,
                             ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
