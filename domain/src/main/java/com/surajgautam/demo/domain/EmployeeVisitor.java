package com.surajgautam.demo.domain;

public interface EmployeeVisitor extends Visitor {

    void setName(String name);

    void setImage(String image);

    void setDescription(String description);

    void setDateLastEdited(String dateLastEdited);
    
}
