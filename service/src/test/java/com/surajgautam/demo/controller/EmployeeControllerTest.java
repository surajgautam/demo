package com.surajgautam.demo.controller;

import com.surajgautam.demo.domain.Employee;
import com.surajgautam.demo.domain.EmployeeParameter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by Suraj Gautam.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {

    @LocalServerPort
    private int port;

    @Test
    void givenValidRequest_whenGet_ThenStatusOK() {
        final String url = "http://localhost:" + port + "/api/v1/employees";
        final String paginationUrl = "http://localhost:" + port + "/api/v1/employees?page=1&size=20";
        final String searchUrl = "http://localhost:" + port + "/api/v1/employees?name=Operations";

        PageResource resource = get(url, HttpStatus.OK.value(), PageResource.class);
        assertEquals(10, resource.getContent().size());
        assertEquals(0, resource.getPageNumber());
        assertEquals(100, resource.getTotalElements());

        PageResource pageResource = get(paginationUrl, HttpStatus.OK.value(), PageResource.class);

        assertEquals(20, pageResource.getContent().size());
        assertEquals(1, pageResource.getPageNumber());
        assertEquals(100, pageResource.getTotalElements());

        List response = get(searchUrl, HttpStatus.OK.value(), PageResource.class).getContent();
        response.forEach(o -> {
            LinkedHashMap linkedHashMap = (LinkedHashMap) o;
            String name = (String) linkedHashMap.get("name");
            assertThat(name).contains("Operations");
        });
    }

    @Test
    void givenValidRequest_whenCreate_ThenStatusCreated() {
        final String url = "http://localhost:" + port + "/api/v1/employees";
        final String searchUrl = "http://localhost:" + port + "/api/v1/employees?name=Suraj&description=Suraj image";

        EmployeeParameter requestBody = createEmployeeRequest();

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestBody)
                .post(url)
                .then()
                .statusCode(HttpStatus.CREATED.value());

        PageResource pageResource = get(searchUrl, HttpStatus.OK.value(), PageResource.class);
        assertNotNull(pageResource);
        pageResource.getContent().forEach(o -> {
            LinkedHashMap linkedHashMap = (LinkedHashMap) o;
            String name = (String) linkedHashMap.get("name");
            assertEquals("Suraj", name);
            String description = (String) linkedHashMap.get("description");
            assertEquals("Suraj image", description);
        });
    }


    private EmployeeParameter createEmployeeRequest() {
        return new EmployeeRequest("Suraj", "http://test.com", "Suraj image", LocalDate.now().toString());
    }

    private <T> T get(String url, int statusCode, Class<T> tClass) {
        return given().get(url)
                .then().statusCode(statusCode)
                .extract()
                .as(tClass);
    }
}