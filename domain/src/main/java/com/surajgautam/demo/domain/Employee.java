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
public final class Employee implements Visitable<EmployeeVisitor> {
    @Id
    private String id;
    private String name;
    private String image;
    private String description;
    private String dateLastEdited;

    public static Employee create(EmployeeParameter params) {
        return new Employee(null, params.getName(), params.getImage(), params.getDescription(), params.getDateLastEdited());
    }

    @Override
    public void accept(EmployeeVisitor visitor) {
        visitor.setName(name);
        visitor.setImage(image);
        visitor.setDescription(description);
        visitor.setDateLastEdited(dateLastEdited);
    }
}
