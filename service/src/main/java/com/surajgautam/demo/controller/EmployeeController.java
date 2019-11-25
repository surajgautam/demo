package com.surajgautam.demo.controller;

import com.surajgautam.demo.constants.ResourceConstants;
import com.surajgautam.demo.domain.Employee;
import com.surajgautam.demo.domain.EmployeeFilter;
import com.surajgautam.demo.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
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
        Page<Employee> page;
        Employee employee = Employee.create(filter);
        if(employee.getName()==null || employee.getDescription()==null){
            page =   repository.findAll(pageable);
        }else{
            page = repository.findAll(employee, pageable);
        }
        return ResponseEntity.ok(toPageResource(page));

    }

    private PageResource<Employee> toPageResource(Page<Employee> page) {
        PageResource<Employee> resource = new PageResource<>();
        resource.setContent(page.getContent());
        resource.setPageNumber(page.getPageable().getPageNumber());
        resource.setTotalElements(page.getTotalElements());
        return resource;
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
        savedEmployee.update(updateableEmployee);
        repository.save(savedEmployee);
        return ResponseEntity.ok(null);
    }

}
