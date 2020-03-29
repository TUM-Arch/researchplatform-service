package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.model.Field;
import de.tum.ar.researchplatform.service.field.FieldService;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.*;

/**
 * Created by karthik on 3/29/2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FieldControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private String endpoint = "/api/fields";

    @Autowired
    private FieldService fieldService;

    @Before
    public void setup() throws Exception {
        RestAssured.port = port;
        // Create two objects
        Field field1 = new Field();
        Field field2 = new Field();
        fieldService.saveOrUpdate(field1);
        fieldService.saveOrUpdate(field2);
    }

    @After
    public void breakdown() {
        fieldService.deleteAll();
    }

    @Test
    public void testGET_OK() {
        when()
                .request(Method.GET, endpoint)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @Ignore
    public void testDELETE_OK() {
        //TODO: Remove Ignore annotation after adding endpoint
        when()
                .request(Method.DELETE, endpoint)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testPOST_OK() {
        //TODO: Add test
    }
}
