package com.surajgautam.demo.controller;

import com.surajgautam.demo.domain.EmployeeParameter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;

import static com.surajgautam.demo.constants.ResourceConstants.BASE_URL;
import static com.surajgautam.demo.constants.ResourceConstants.EmployeeResource.URL;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
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
        final String url = "http://localhost:" + port + BASE_URL + URL;
        final String paginationUrl = "http://localhost:" + port + BASE_URL + URL + "?page=1&size=20";
        final String searchUrl = "http://localhost:" + port + BASE_URL + URL + "?name=Operations";

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
        final String url = "http://localhost:" + port + BASE_URL + URL;
        final String searchUrl = "http://localhost:" + port + BASE_URL + URL + "?name=Suraj&description=Suraj image";

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

    @Test
    void givenValidRequest_whenDelete_ThenStatusOk() {
        final String url = "http://localhost:" + port + BASE_URL + URL;
        PageResource resource = get(url, HttpStatus.OK.value(), PageResource.class);

        assertEquals(100, resource.getTotalElements());

        String id = getId(resource);

        given()
                .delete(url + "/" + id)
                .then()
                .statusCode(HttpStatus.OK.value());

        PageResource newResponse = get(url, HttpStatus.OK.value(), PageResource.class);
        assertEquals(99, newResponse.getTotalElements());

    }

    @Test
    void givenValidRequest_whenUpdate_ThenStatusOk() {
        final String url = "http://localhost:" + port + BASE_URL + URL;

        PageResource resource = get(url, HttpStatus.OK.value(), PageResource.class);
        String id = getId(resource);

        final String path = url + "/" + id;

        EmployeeRequest payload = createEmployeeRequest();
        EmployeeResponse employeeResponse = get(path, HttpStatus.OK.value(), EmployeeResponse.class);

        assertNotNull(employeeResponse);
        assertNotEquals(employeeResponse.getName(), payload.getName());
        assertNotEquals(employeeResponse.getImage(), payload.getImage());
        assertNotEquals(employeeResponse.getDescription(), payload.getDescription());
        assertNotEquals(employeeResponse.getDateLastEdited(), payload.getDateLastEdited());

        given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(payload)
                .put(path)
                .then()
                .statusCode(HttpStatus.OK.value());

        EmployeeResponse updatedResponse = get(path, HttpStatus.OK.value(), EmployeeResponse.class);

        assertNotNull(employeeResponse);
        assertEquals(payload.getName(), updatedResponse.getName());
        assertEquals(payload.getImage(), updatedResponse.getImage());
        assertEquals(payload.getDescription(), updatedResponse.getDescription());
        assertEquals(payload.getDateLastEdited(), updatedResponse.getDateLastEdited());


    }

    private EmployeeRequest createEmployeeRequest() {
        return new EmployeeRequest("Suraj", "http://test.com", "Suraj image", LocalDate.now().toString());
    }

    private String getId(PageResource resource) {
        LinkedHashMap linkedHashMap = (LinkedHashMap) resource.getContent().get(0);
        return (String) linkedHashMap.get("id");
    }

    private <T> T get(String url, int statusCode, Class<T> tClass) {
        return given().get(url)
                .then().statusCode(statusCode)
                .extract()
                .as(tClass);
    }
}
