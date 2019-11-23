package com.surajgautam.demo.repository;

import com.surajgautam.demo.domain.Employee;

import java.util.List;

/**
 * Created by Suraj Gautam.
 */
public interface EmployeeRepository {

    Employee save(Employee employee);

    void saveAll(List<Employee> employees);

    List<Employee> findAll();

}
