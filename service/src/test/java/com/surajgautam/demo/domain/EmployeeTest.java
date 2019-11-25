package com.surajgautam.demo.domain;

import com.surajgautam.demo.controller.EmployeeRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void canCreate() {

        EmployeeParameter params = new EmployeeRequest("Suraj", "Suraj Image",
                "Suraj Description", LocalDate.now().toString());

        Employee employee = Employee.create(params);

        assertNotNull(employee);
        assertEquals(employee.getImage(), params.getImage());
        assertEquals(employee.getName(), params.getName());
        assertEquals(employee.getDescription(), params.getDescription());
        assertEquals(employee.getDateLastEdited(), params.getDateLastEdited());

    }

    @Test
    void givenInvalidParams_whenCreate_ThenThrowsException() {
        EmployeeParameter params = new EmployeeRequest(null, null, null, null);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Employee.create(params));
        assertEquals(exception.getMessage(), "name.cannot.be.null");
    }

    @Test
    void givenNullNameAndDescription_whenIsNameAndDescriptionNull_ThenReturnTrue() {
        EmployeeFilter employeeFilter = new EmployeeFilter(null, null);
        Employee employee = Employee.create(employeeFilter);
        assertTrue(employee.isNameAndDescriptionNull());
    }
}
