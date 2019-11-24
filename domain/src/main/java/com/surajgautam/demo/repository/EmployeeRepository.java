package com.surajgautam.demo.repository;

import com.surajgautam.demo.domain.Employee;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Created by Suraj Gautam.
 */
public interface EmployeeRepository {

    Employee save(Employee employee);

    void saveAll(List<Employee> employees);

    Page<Employee> findAll(Employee employee, Pageable pageable);

    Page<Employee> findAll(Pageable pageable);

    void delete(String id);

    Optional<Employee> findById(String id);
}
