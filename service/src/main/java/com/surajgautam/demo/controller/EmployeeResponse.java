package com.surajgautam.demo.controller;

import com.surajgautam.demo.domain.EmployeeVisitor;
import lombok.Getter;

@Getter
public class EmployeeResponse implements EmployeeVisitor {

    private String name;
    private String image;
    private String description;
    private String dateLastEdited;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setDateLastEdited(String dateLastEdited) {
        this.dateLastEdited = dateLastEdited;
    }
}
