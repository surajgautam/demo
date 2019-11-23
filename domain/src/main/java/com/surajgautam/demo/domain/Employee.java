package com.surajgautam.demo.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

/**
 * Created by Suraj Gautam.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Employee {
    @Id
    private String id;
    private String name;
    private String image;
    private String description;
    private String dateLastEdited;

    public static Employee create(EmployeeRequest request){
        return new Employee(null,request.getName(), request.getImage(), request.getDescription(), request.getDescription());
    }

}
