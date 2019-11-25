package com.surajgautam.demo.controller;

import com.surajgautam.demo.constants.ResourceConstants;
import com.surajgautam.demo.domain.Employee;
import com.surajgautam.demo.domain.EmployeeFilter;
import com.surajgautam.demo.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Suraj Gautam.
 */
@RestController
@RequestMapping(value = ResourceConstants.BASE_URL + ResourceConstants.EmployeeResource.URL)
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeRepository repository;

    @GetMapping
    public ResponseEntity<PageResource<Employee>> getEmployees(@PageableDefault Pageable pageable,
                                                               EmployeeFilter filter) {
        Employee employee = Employee.create(filter);
        return ResponseEntity.ok(toPageResource(repository.findAll(employee, pageable)));

    }

    private PageResource<Employee> toPageResource(Page<Employee> page) {
        PageResource<Employee> resource = new PageResource<>();
        resource.setContent(page.getContent());
        resource.setPageNumber(page.getPageable().getPageNumber());
        resource.setTotalElements(page.getTotalElements());
        return resource;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody EmployeeRequest request) {
        repository.save(Employee.create(request));
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable(value = "id") String id) {
        repository.delete(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateEmployee(@RequestBody EmployeeRequest request,
                                               @PathVariable(value = "id") String id) {
        //throw 404 instead
        Employee savedEmployee = repository.findById(id).orElseThrow(UnsupportedOperationException::new);
        Employee updateableEmployee = Employee.create(request);
        savedEmployee.update(updateableEmployee);
        repository.save(savedEmployee);
        return ResponseEntity.ok(null);
    }

}
