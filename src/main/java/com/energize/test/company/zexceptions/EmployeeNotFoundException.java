package com.energize.test.company.zexceptions;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String message) { super(message); }
}
