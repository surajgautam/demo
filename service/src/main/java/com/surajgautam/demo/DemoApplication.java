package com.surajgautam.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.surajgautam.demo.domain.Employee;
import com.surajgautam.demo.repository.EmployeeDataRepository;
import com.surajgautam.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

@SpringBootApplication
@EnableMongoRepositories(considerNestedRepositories = true)
@RequiredArgsConstructor
public class DemoApplication {

	private final EmployeeRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner (MongoOperations mongoOperations){
		mongoOperations.dropCollection(Employee.class);
		return (args)->{
			System.out.println("test");
			loadToDatabase();
		};
	};

	@SneakyThrows
	public void loadToDatabase(){
		InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("mock_data.json");
		ObjectMapper objectMapper = new ObjectMapper();
		List<Employee> employees = asList(objectMapper.readValue(resourceAsStream, Employee[].class));
		repository.saveAll(employees);
	}
}
