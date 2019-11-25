package com.surajgautam.demo.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Objects;

import static org.springframework.util.Assert.notNull;

/**
 * Created by Suraj Gautam.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Employee implements Visitable<EmployeeVisitor> {
    @Id
    private String id;
    private String name;
    private String image;
    private String description;
    private String dateLastEdited;

    public static Employee create(EmployeeParameter params) {
        notNull(params.getName(), "name.cannot.be.null");
        notNull(params.getImage(), "image.cannot.be.null");
        notNull(params.getDescription(), "description.cannot.be.null");
        notNull(params.getDateLastEdited(), "date.last.edited.cannot.be.null");
        return new Employee(null, params.getName(), params.getImage(), params.getDescription(), params.getDateLastEdited());
    }

    public static Employee create(EmployeeFilter filter) {
        return new Employee(null, filter.getName(), null, filter.getDescription(), null);
    }


    @Override
    public void accept(EmployeeVisitor visitor) {
        visitor.setName(name);
        visitor.setImage(image);
        visitor.setDescription(description);
        visitor.setDateLastEdited(dateLastEdited);
    }

    public boolean isNameAndDescriptionNull() {
        return Objects.isNull(this.name) && Objects.isNull(this.description);
    }

    public void update(Employee updatableEmployee) {
        this.name = updatableEmployee.getName();
        this.image = updatableEmployee.getImage();
        this.dateLastEdited = updatableEmployee.getDateLastEdited();
        this.description = updatableEmployee.getDescription();
    }
}
