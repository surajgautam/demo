package com.surajgautam.demo.repository;

import com.surajgautam.demo.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.StringMatcher.CONTAINING;
import static org.springframework.data.domain.ExampleMatcher.matchingAny;

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
    public Page<Employee> findAll(Employee employee, Pageable pageable) {
        if (employee.isNameAndDescriptionNull()) {
            return repository.findAll(pageable);
        }
        Example<Employee> employeeExample = Example.of(employee, matchingAny().withIgnoreNullValues().withStringMatcher(CONTAINING));
        return repository.findAll(employeeExample, pageable);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Employee> findById(String id) {
        return repository.findById(id);
    }

    public interface MongoEmployeeRepositoryImpl extends MongoRepository<Employee, String> {

    }
}
