package com.surajgautam.demo.exception;

import lombok.Getter;

/**
 * Created by Suraj Gautam.
 */
@Getter
public class EmployeeNotFoundException extends RuntimeException {

    private String message;

    public EmployeeNotFoundException() {
    }

    public EmployeeNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
