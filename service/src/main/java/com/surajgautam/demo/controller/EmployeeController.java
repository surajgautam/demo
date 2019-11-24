package com.surajgautam.demo.controller;

import com.surajgautam.demo.constants.ResourceConstants;
import com.surajgautam.demo.domain.Employee;
import com.surajgautam.demo.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Suraj Gautam.
 */
@RestController
@RequestMapping(value = ResourceConstants.BASE_URL + ResourceConstants.EmployeeResource.URL)
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeRepository repository;

    @GetMapping
    public List<Employee> getEmployees(EmployeeResponse employee) {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> create(@RequestBody EmployeeRequest request) {
        Employee employee = repository.save(Employee.create(request));

        EmployeeResponse response = new EmployeeResponse();
        employee.accept(response);

        return ResponseEntity.ok(response);
    }

}
