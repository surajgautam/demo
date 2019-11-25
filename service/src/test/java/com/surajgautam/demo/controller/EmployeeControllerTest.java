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
        PageResource resource = when().get("http://localhost:" + port + "/api/v1/employees")
                .then().statusCode(HttpStatus.OK.value())
                .and().extract().as(PageResource.class);

        assertEquals(resource.getContent().size(), 10);
        assertEquals(resource.getPageNumber(), 0);
        assertEquals(resource.getTotalElements(), 100);

        PageResource pageResource = when().get("http://localhost:" + port + "/api/v1/employees?page=1&size=20")
                .then().statusCode(HttpStatus.OK.value())
                .and().extract().as(PageResource.class);

        assertEquals(20, pageResource.getContent().size());
        assertEquals(1,pageResource.getPageNumber());
        assertEquals(100,pageResource.getTotalElements());


    }
}