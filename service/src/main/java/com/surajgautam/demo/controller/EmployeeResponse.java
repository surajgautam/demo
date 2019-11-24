package com.surajgautam.demo.controller;

import com.surajgautam.demo.domain.EmployeeVisitor;

/**
 * Created by Suraj Gautam.
 */
public class EmployeeResponse implements EmployeeVisitor {

    private String name;

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
