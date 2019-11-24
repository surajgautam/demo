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

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable(value = "id") String id) {
        repository.delete(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateEmployee(@RequestBody EmployeeRequest request,
                                               @PathVariable(value = "id") String id) {
        Employee savedEmployee = repository.findById(id).orElseThrow(UnsupportedOperationException::new);
        Employee updateableEmployee = Employee.create(request);
        applyMergeUpdates(savedEmployee, updateableEmployee);
        repository.save(savedEmployee);
        return ResponseEntity.ok(null);
    }

    private void applyMergeUpdates(Employee savedEmployee, Employee updateableEmployee) {

    }

}
