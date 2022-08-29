package com.energize.test.company.zexceptions;

public class NotValidException extends RuntimeException {

    public NotValidException(String message) {
        super(message);
    }
}
