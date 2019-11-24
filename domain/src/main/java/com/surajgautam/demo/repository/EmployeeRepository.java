package com.surajgautam.demo.repository;

import com.surajgautam.demo.domain.Employee;

import java.util.List;
import java.util.Optional;

/**
 * Created by Suraj Gautam.
 */
public interface EmployeeRepository {

    Employee save(Employee employee);

    void saveAll(List<Employee> employees);

    List<Employee> findAll();

    void delete(String id);

    Optional<Employee> findById(String id);
}
