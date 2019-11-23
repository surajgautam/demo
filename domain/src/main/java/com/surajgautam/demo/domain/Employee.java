package com.surajgautam.demo.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

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

    public static Employee create(String name, String image, String description, String dateLastEdited){
        return new Employee(null,name, image, description, dateLastEdited);
    }

}
