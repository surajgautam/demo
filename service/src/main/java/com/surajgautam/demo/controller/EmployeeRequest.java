package com.surajgautam.demo.controller;

import com.surajgautam.demo.domain.EmployeeParameter;

import java.io.Serializable;

/**
 * Created by Suraj Gautam.
 */
public class EmployeeRequest implements EmployeeParameter, Serializable {
    private String name;
    private String image;
    private String description;
    private String dateLastEdited;

    public EmployeeRequest(String name, String image, String description, String dateLastEdited) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.dateLastEdited = dateLastEdited;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getImage() {
        return this.image;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getDateLastEdited() {
        return this.dateLastEdited;
    }
}
