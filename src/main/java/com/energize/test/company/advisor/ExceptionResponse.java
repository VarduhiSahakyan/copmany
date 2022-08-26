package com.energize.test.company.advisor;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ExceptionResponse {
    private String message;
    private int statusCode;
    private HttpStatus httpStatus;
    private ZonedDateTime timestamp;


    public ExceptionResponse(String message,
                             int statusCode,
                             HttpStatus httpStatus,
                             ZonedDateTime timestamp) {
        this.message = message;
        this.statusCode = statusCode;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public ExceptionResponse() { }

    public String getMessage() { return message; }

    public int getStatusCode() { return statusCode; }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setMessage(String message) { this.message = message; }

    public void setStatusCode(int statusCode) { this.statusCode = statusCode; }

    public void setHttpStatus(HttpStatus httpStatus) { this.httpStatus = httpStatus; }

    public void setTimestamp(ZonedDateTime timestamp) { this.timestamp = timestamp; }
}
