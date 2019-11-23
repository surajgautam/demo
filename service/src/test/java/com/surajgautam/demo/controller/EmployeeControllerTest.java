package com.surajgautam.demo.controller;

import com.surajgautam.demo.domain.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Suraj Gautam.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {

    @LocalServerPort
    private int port;

    @Test
    void givenValidRequest_whenGet_ThenStatusOK() {
        Employee[] employees = when().get("http://localhost:" + port + "/api/v1/employees")
                .then().statusCode(HttpStatus.OK.value())
                .and().extract().as(Employee[].class);
    }
}