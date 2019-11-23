package com.surajgautam.demo.controller;

import com.surajgautam.demo.constants.ResourceConstants;
import com.surajgautam.demo.domain.Employee;
import com.surajgautam.demo.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Suraj Gautam.
 */
@RestController
@RequestMapping(value = ResourceConstants.BASE_URL+ResourceConstants.EmployeeResource.URL)
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeRepository repository;

    @GetMapping
    public List<Employee> getEmployees(EmployeeResponse employee){
        return repository.findAll();
    }

}
