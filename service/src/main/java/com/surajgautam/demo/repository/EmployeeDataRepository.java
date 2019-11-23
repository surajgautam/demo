package com.surajgautam.demo.repository;

import com.surajgautam.demo.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Suraj Gautam.
 */
@Repository
@RequiredArgsConstructor
public class EmployeeDataRepository implements EmployeeRepository {

    private final MongoEmployeeRepositoryImpl repository;

    @Override
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public void saveAll(List<Employee> employees) {
        repository.saveAll(employees);
    }

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    public interface MongoEmployeeRepositoryImpl extends MongoRepository<Employee, String>{

    }
}
