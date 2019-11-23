package com.surajgautam.demo.controller;

import lombok.Getter;

import java.time.LocalDate;

/**
 * Created by Suraj Gautam.
 */
@Getter
public class EmployeeRequest {
    private String name;
    private String image;
    private String description;
    private LocalDate dateLastEdited;
}
